package cn.root.service.impl;


import cn.root.bean.entity.Activity;
import cn.root.bean.entity.User;
import cn.root.bean.req.user.LoginReq;
import cn.root.bean.req.user.RegistryReq;
import cn.root.bean.req.user.UserUpdatePasswordReq;
import cn.root.bean.resp.activity.PageActivityResp;
import cn.root.bean.resp.user.UserLoginResp;
import cn.root.bean.resp.user.UserPublicResp;
import cn.root.common.CommonString;
import cn.root.common.Constant;
import cn.root.common.ResponseCode;
import cn.root.common.ResponseResult;
import cn.root.mapper.UserMapper;
import cn.root.service.IActivityService;
import cn.root.service.IUserService;
import cn.root.util.HttpContextUtils;
import cn.root.util.IpUtils;
import cn.root.util.JWTUtils;
import cn.root.util.PasswordDesensitization;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static cn.root.common.CommonString.ACCOUNT_IN_RISK_IP;
import static cn.root.common.ResponseCode.ACCOUNT_PWD_NOT_EXIST;


@Service
@Slf4j
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IActivityService activityService;

    @Autowired
    private IdentifierGenerator identifierGenerator;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    private User findUserByAccount(String username) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserName, username));
    }

    @Override
    @Transient
    public ResponseResult<Integer> register(RegistryReq registryReq) {

        User sysUser = findUserByAccount(registryReq.getUserName());
        if (sysUser != null) {
            log.debug("注册失败");
            return ResponseResult.fail(ResponseCode.ACCOUNT_EXIST.getCode(), "账户已经被注册了");
        }

        User user = new User();
        String uuid = identifierGenerator.nextUUID(user);
        String time = new Date().toString();
        String salt = uuid.substring(0, 6) + time.substring(0, 6);
        String password = PasswordDesensitization.desensitization(registryReq.getPassword(), salt);

        user.setId(uuid);
        user.setUserName(registryReq.getUserName());
        user.setPassword(password);
        user.setNickName(registryReq.getNickName());
        user.setIp(IpUtils.getIpAddress());
        user.setTime(time);
        user.setSalt(salt);

        userMapper.insert(user);

        return ResponseResult.success();
    }

    @Override
    public ResponseResult<Integer> updatePassword(UserUpdatePasswordReq userUpdatePasswordReq) {

        User user = userMapper.selectById(HttpContextUtils.getHttpServletRequest().getHeader("access-id"));
        user.setPassword(PasswordDesensitization
                .desensitization(userUpdatePasswordReq.getNewPassword(), user.getSalt()));
        userMapper.updateById(user);

        return ResponseResult.success();
    }

    @Override
    public ResponseResult<Boolean> checkOldPassword(String oldPassword) {
        String userId = HttpContextUtils.getHttpServletRequest().getHeader("access-id");
        User user = userMapper.selectById(userId);
        String desensitization = PasswordDesensitization.desensitization(oldPassword, userId);
        if (user.getPassword().equals(desensitization)) {
            return ResponseResult.success(true);
        }
        return ResponseResult.success(false);
    }

    @Override
    public ResponseResult<UserLoginResp> login(LoginReq loginReq) {

//        System.out.println("收到的参数为----------" + loginReq);

        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserName, loginReq.getUserName()));

        if (user == null || !user.getPassword()
                .equals(PasswordDesensitization
                        .desensitization(loginReq.getPassword(), user.getId())))
            return ResponseResult.fail(ACCOUNT_PWD_NOT_EXIST);

        String id = user.getId();
        String token = JWTUtils.createToken(id, user.getSalt());
        stringRedisTemplate.opsForValue().set(token, id, Constant.expireTime, TimeUnit.SECONDS);

        String ip = user.getIp();
        String ipAddress = IpUtils.getIpAddress();
        UserLoginResp userLoginResp = new UserLoginResp();
        userLoginResp.setToken(token);
        userLoginResp.setNickName(user.getNickName());
        userLoginResp.setId(user.getId());
        if (!ip.equals(ipAddress)) {
            user.setIp(ipAddress);
            userMapper.updateById(user);
            userLoginResp.setMessage(ACCOUNT_IN_RISK_IP.getName());
        }
        return ResponseResult.success(userLoginResp);
    }

    @Override
    public ResponseResult<Integer> logout() {
        String token = HttpContextUtils.getHttpServletRequest()
                .getHeader("access-token");
        System.out.println("参数为-------" + token);
        stringRedisTemplate.opsForValue().getOperations().delete(token);
        System.out.println("-----退出登录成功-----");
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<PageActivityResp> viewOther(String id) {

        PageActivityResp pageActivityResp = new PageActivityResp();
        List<Activity> range = redisTemplate.opsForList().range(CommonString.LIST_ACTIVITIES.getName()
                , 0
                , redisTemplate.opsForList().size(CommonString.LIST_ACTIVITIES.getName()));
        range = range
                .stream()
                .filter(activity -> activity.getCreateBy().equals(id))
                .collect(Collectors.toList());
        pageActivityResp.setList(activityService.transform(range));
        pageActivityResp.setCurrentPage(1);
        pageActivityResp.setCounts(range.size());
        log.info("------------->从redis中获取，再用stream流转,共获取" + range.size() + "个数据");
        return ResponseResult.success(pageActivityResp);
    }


}
