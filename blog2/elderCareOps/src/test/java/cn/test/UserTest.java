package cn.test;

import cn.root.Application;
import cn.root.bean.req.user.LoginReq;
import cn.root.bean.req.user.RegistryReq;
import cn.root.bean.req.user.UserUpdatePasswordReq;
import cn.root.common.ResponseResult;
import cn.root.controller.UserController;
import cn.root.service.IUserService;
import cn.root.util.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

@SpringBootTest(classes = Application.class)
public class UserTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserController userController;

    /**
     * 注册
     */
    @Test
    public void registry(){
        RegistryReq registryReq = new RegistryReq("gou","123456","成一庚");
        userService.register(registryReq);
    }

    /**
     * 更改密码
     */
    @Test
    public void changePassword(){
//        UserUpdatePasswordReq a = new UserUpdatePasswordReq("8a933c3b71bdae47cdbaf25bd6de4328", "111111", "成一庚");
//        userService.updatePassword(a);
    }

    /**
     * 登录
     */
    @Test
    public void loginTest(){
        LoginReq cyg = new LoginReq("cyg3", "111111");
        userService.login(cyg);
    }

    /**
     * 退出登录
     */
    @Test
    public void logoutTest(){
//        userService.logout("eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NTE1NDcyMjQsInVzZXJJZCI6ImE2NmRkMzAxYmNkMTM3NzFiYzRjZGY3ZWEwYTA2MWJlIiwiaWF0IjoxNjUxNTQzNjI0fQ.kZJV5wAamUR8WxoWd35D-hB8TqgNh-53RHJ-tXE8ZMo");
    }


    /**
     * 用户查看其它用户信息
     */
    @Test
    public void viewOtherTest(){
//        userController.viewOther("a66dd301bcd13771bc4cdf7ea0a061be"
//                ,"eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NTE2MzI5NDUsInVzZXJJZCI6ImE2NmRkMzAxYmNkMTM3NzFiYzRjZGY3ZWEwYTA2MWJlIiwiaWF0IjoxNjUxNTQ2NTQ1fQ.5gBPuC2c5plP8XiAHCSG2_LaXg6osF9UZ-LLM4OFYpk"
//                ,"a66dd301bcd13771bc4cdf7ea0a061be"
//        );
    }


    /**
     * 验证JWT
     */
    @Test
    public void q(){
        Map<String, Object> map = JWTUtils.checkToken("eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NTE2MzI5NDUsInVzZXJJZCI6ImE2NmRkMzAxYmNkMTM3NzFiYzRjZGY3ZWEwYTA2MWJlIiwiaWF0IjoxNjUxNTQ2NTQ1fQ.5gBPuC2c5plP8XiAHCSG2_LaXg6osF9UZ-LLM4OFYpk",
                "a66dd3");
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for(Map.Entry<String, Object> e : entries){
            System.out.println(e);
        }
    }
}
