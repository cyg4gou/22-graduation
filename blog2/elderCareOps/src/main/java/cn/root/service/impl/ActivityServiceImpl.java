package cn.root.service.impl;

import cn.root.bean.entity.Activity;
import cn.root.bean.entity.ActivityBody;
import cn.root.bean.entity.User;
import cn.root.bean.entity.ViewCount;
import cn.root.bean.req.activity.PageActivityReq;
import cn.root.bean.req.activity.PublishReq;
import cn.root.bean.resp.activity.ActivityBodyResp;
import cn.root.bean.resp.activity.ActivityResp;
import cn.root.bean.resp.activity.PageActivityResp;
import cn.root.common.CommonString;
import cn.root.common.Constant;
import cn.root.common.ResponseResult;
import cn.root.mapper.ActivityBodyMapper;
import cn.root.mapper.ActivityMapper;
import cn.root.mapper.UserMapper;
import cn.root.mapper.ViewCountMapper;
import cn.root.service.IActivityService;
import cn.root.util.ActivityRefresh;
import cn.root.util.HttpContextUtils;
import cn.root.util.JWTUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
@Transactional
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ActivityBodyMapper activityBodyMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IdentifierGenerator identifierGenerator;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ViewCountMapper viewCountMapper;

    @Autowired
    private ActivityRefresh activityRefresh;

    @Override
    public ResponseResult<PageActivityResp> pageActivity(PageActivityReq pageActivityReq) {
        PageActivityResp pageActivityResp = new PageActivityResp();
        Integer currentPage = pageActivityReq.getCurrentPage();
        Integer pageSize = pageActivityReq.getPageSize();
        Long expectSize = Long.valueOf(currentPage * pageSize);
        Long actualAllSize = redisTemplate.opsForList().size(CommonString.LIST_ACTIVITIES.getName());
        long actualUserSize = actualAllSize;

        List<Activity> range = redisTemplate.opsForList().range(CommonString.LIST_ACTIVITIES.getName()
                , 0
                , actualUserSize - 1);
//        for (Activity activity : range) {
//            System.out.println(activity);
//        }
//        System.out.println("=======================");
//        range = range.stream()
//                .filter(activity -> activity.getMark() == 1)
//                .collect(Collectors.toList());
//        for (Activity activity : range) {
//            System.out.println(activity);
//        }
        String authorId = pageActivityReq.getAuthorId();

        List<String> userIDRange = redisTemplate.opsForList()
                .range(CommonString.LIST_USER.getName()
                        , 0
                        , redisTemplate.opsForList().size(CommonString.LIST_USER.getName()));

        if (userIDRange.contains(authorId) && !StringUtils.isBlank(authorId)) {
            range = (List<Activity>) redisTemplate.opsForHash()
                    .get(CommonString.LIST_USER_ACTIVITY.getName()
                            , authorId);
            actualUserSize = Long.valueOf(range.size());

        } else if (!StringUtils.isBlank(authorId)) {

            userIDRange.add(authorId);
            redisTemplate.opsForList()
                    .rightPush(CommonString.LIST_USER.getName()
                            , authorId);

            range = range.stream()
                    .filter(activity -> activity.getCreateBy().equals(authorId))
                    .collect(Collectors.toList());

            currentPage = 1;
            expectSize = Long.valueOf(currentPage * pageSize);
            actualUserSize = Long.valueOf(range.size());

            redisTemplate.opsForHash().put(CommonString.LIST_USER_ACTIVITY.getName()
                    , authorId
                    , range);


        }

        pageActivityResp.setCounts(Math.toIntExact(range.size()));

        int pastSize = (currentPage - 1) * pageSize;
        range = range.stream()
                .skip(pastSize)
                .limit(expectSize > actualUserSize
                        ? actualUserSize - pastSize
                        : expectSize - pastSize)
                .collect(Collectors.toList());

        pageActivityResp.setCurrentPage(currentPage);
        pageActivityResp.setList(this.transform(range));
        pageActivityResp.setCounts(Math.toIntExact(actualUserSize));

        return ResponseResult.success(pageActivityResp);
    }

    @Override
    public ResponseResult<Integer> publish(PublishReq publishReq) {

//        System.out.println("收到的参数------>" + publishReq);

        User user = userMapper.selectById(publishReq.getId());
        String token = publishReq.getToken();
        Map<String, Object> map = JWTUtils.checkToken(token, user.getSalt());
        String createUserId = publishReq.getId();
        if (createUserId.equals(map.get("userId"))) {
            Activity activity = new Activity();
            ActivityBody activityBody = new ActivityBody();
            String id = identifierGenerator.nextUUID(activity);

            activity.setId(id);
            activity.setBodyId(id);
            activity.setCreateBy(createUserId);
            activity.setExpireTime(publishReq.getExpireTime());
            activity.setTitle(publishReq.getTitle());

            activityBody.setId(id);
            activityBody.setBody(HtmlUtils.htmlEscapeHex(publishReq.getBody()));

            activityBodyMapper.insert(activityBody);
            activityMapper.insert(activity);


//            List<Activity> range = redisTemplate.opsForList().range(CommonString.LIST_ACTIVITIES.getName()
//                    , 0
//                    , redisTemplate.opsForList().size(CommonString.LIST_ACTIVITIES.getName()));
//            redisTemplate.opsForList().getOperations().delete(CommonString.LIST_ACTIVITIES.getName());
//            range.add(0, activity);
            log.info("新增的活动为---------->" + activity);
            /*range = range.stream()
                    .sorted(Comparator.comparing(Activity::getCreateTime))
                    .collect(Collectors.toList());*/
            redisTemplate.opsForList().leftPush(CommonString.LIST_ACTIVITIES.getName(), activity);
            redisTemplate.opsForList().rightPush(CommonString.LIST_ACTIVITY_BODY.getName(), activityBody);

            log.info("<<<<<<<<<<<<<<<<<<发布活动成功，正在刷新缓存，并排序>>>>>>>>>>>>>>>>>>");
        }
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<ActivityBodyResp> viewContent(String activityId) {

        boolean viewCountAdd = false;
        String userId = HttpContextUtils.getHttpServletRequest().getHeader("access-id");
        ViewCount viewCount = viewCountMapper.selectOne(new LambdaQueryWrapper<ViewCount>()
                .eq(ViewCount::getActivityId, activityId)
                .eq(ViewCount::getUserId, userId)
                .eq(ViewCount::getIfDelete, Constant.notDeleted));

        if (viewCount == null) {
            ViewCount insertViewCount = new ViewCount();
            insertViewCount.setActivityId(activityId);
            insertViewCount.setUserId(userId);
            viewCountMapper.insert(insertViewCount);
            viewCountAdd = true;
        }

        List<Activity> range = redisTemplate.opsForList().range(CommonString.LIST_ACTIVITIES.getName()
                , 0
                , redisTemplate.opsForList().size(CommonString.LIST_ACTIVITIES.getName()));
        Activity activity = range.stream()
                .filter((theActivity) -> Objects.equals(theActivity.getId(), activityId))
                .collect(Collectors.toList())
                .get(0);

        if (viewCountAdd) {
            int abs = (int) Math.round(Math.random() * 10);
            activity.setViewCount(activity.getViewCount() + abs);
            activityMapper.updateById(activity);
            log.info("------------->viewCount++");
        }

        List<ActivityBody> rangeActivityBody = redisTemplate.opsForList().range(CommonString.LIST_ACTIVITY_BODY.getName()
                , 0
                , redisTemplate.opsForList().size(CommonString.LIST_ACTIVITY_BODY.getName()));

        ActivityBody activityBody = rangeActivityBody.stream()
                .filter((theActivityBody) -> theActivityBody.getId().equals(activityId))
                .collect(Collectors.toList())
                .get(0);
        ActivityBodyResp activityBodyResp = new ActivityBodyResp();
        BeanUtils.copyProperties(activity, activityBodyResp);
        activityBodyResp.setBody(HtmlUtils.htmlUnescape(activityBody.getBody()));
        return ResponseResult.success(activityBodyResp);
    }

    @Override
    public ResponseResult<Integer> deleteAll(String[] activitiesToDelete) {
        for (String id : activitiesToDelete) {
            Activity activity = activityMapper.selectById(id);
            activity.setIfDelete(Constant.delete);
            activityMapper.updateById(activity);
        }
        activityRefresh.refresh();
        activityRefresh.refreshBody();
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<Integer> delete(String activityId) {
        Activity activity = activityMapper.selectById(activityId);
        activity.setIfDelete(Constant.delete);
        activityMapper.updateById(activity);
        activityRefresh.refresh();
        log.info(activityId + ">>>>>>>>>>删除成功");
        return ResponseResult.success();
    }


    /**
     * 内部使用
     * <p>
     * 将List<Activity>转为List<ActivityResp>
     */
    private List<ActivityResp> transform(Activity activity) {
        List<Activity> activityList = new ArrayList<>();
        activityList.add(activity);
        return transform(activityList);
    }

    /**
     * 内部接口
     *
     * @param activities a
     * @return a
     */
    public List<ActivityResp> transform(List<Activity> activities) {
        List<ActivityResp> activityRespList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        for (Activity activity : activities) {
            ActivityResp activityResp = new ActivityResp();
            BeanUtils.copyProperties(activity, activityResp);
            String userId = activity.getCreateBy();
            if (!map.containsKey(userId)) {
                map.put(userId
                        , userMapper.selectById(userId).getNickName());
            }
            activityResp.setAuthorNickName(map.get(userId));
            activityRespList.add(activityResp);
        }
        return activityRespList;
    }
}
