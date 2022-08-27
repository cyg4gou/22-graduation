package cn.root.life.init;

import cn.root.bean.entity.Activity;
import cn.root.bean.entity.ActivityBody;
import cn.root.bean.req.activity.PageActivityReq;
import cn.root.common.CommonString;
import cn.root.mapper.ActivityBodyMapper;
import cn.root.mapper.ActivityMapper;
import cn.root.util.ActivityFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Order(1)
@Component
@Slf4j
public class ListActivityInit implements ApplicationRunner, DisposableBean {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ActivityBodyMapper activityBodyMapper;


    @Override
    public void run(ApplicationArguments args) {
        log.info("***************listActivityInit初始化开始*****************");
        redisTemplate.delete(CommonString.LIST_ACTIVITIES.getName());
        redisTemplate.delete(CommonString.LIST_ACTIVITY_BODY.getName());
        redisTemplate.delete(CommonString.LIST_USER.getName());
        List<Activity> activities = activityMapper.listAllActivityTitle(new PageActivityReq());
        activities = ActivityFilter.filter(activities);
        List<ActivityBody> activityBodies = activityBodyMapper.selectList(null);
        List<String> users = new ArrayList<>();
        users.add("cyg");
        redisTemplate.opsForList().rightPushAll(CommonString.LIST_ACTIVITIES.getName(), activities);
        redisTemplate.opsForList().rightPushAll(CommonString.LIST_ACTIVITY_BODY.getName(), activityBodies);
        redisTemplate.opsForList().rightPushAll(CommonString.LIST_USER.getName(), users);
        log.info("***************listActivityInit初始化成功*****************");
    }

    @Override
    public void destroy() {
        System.out.println("正在销毁>>>>>>>>>>>>>>>>List_activity");
        redisTemplate.delete(CommonString.LIST_ACTIVITIES.getName());
        redisTemplate.delete(CommonString.LIST_ACTIVITY_BODY.getName());
        System.out.println("<<<<<<<<<<<<<<<<List_activity销毁成功");
    }

}
