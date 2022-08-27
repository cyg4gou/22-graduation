package cn.root.util;

import cn.root.bean.entity.Activity;
import cn.root.bean.entity.ActivityBody;
import cn.root.bean.req.activity.PageActivityReq;
import cn.root.common.CommonString;
import cn.root.mapper.ActivityBodyMapper;
import cn.root.mapper.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActivityRefresh {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ActivityBodyMapper activityBodyMapper;

    public void refresh(){
        redisTemplate.delete(CommonString.LIST_ACTIVITIES.getName());
        List<Activity> activities = activityMapper.listAllActivityTitle(new PageActivityReq());
        activities = ActivityFilter.filter(activities);
        redisTemplate.opsForList().rightPushAll(CommonString.LIST_ACTIVITIES.getName(),activities);
    }

    public void refreshBody(){
        redisTemplate.delete(CommonString.LIST_ACTIVITY_BODY.getName());
        List<ActivityBody> activityBodies = activityBodyMapper.selectList(null);
        redisTemplate.opsForList().rightPushAll(CommonString.LIST_ACTIVITY_BODY.getName(),activityBodies);
    }
}
