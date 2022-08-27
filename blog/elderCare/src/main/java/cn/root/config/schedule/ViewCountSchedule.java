package cn.root.config.schedule;

import cn.root.bean.entity.Activity;
import cn.root.bean.req.activity.PageActivityReq;
import cn.root.common.CommonString;
import cn.root.mapper.ActivityMapper;
import cn.root.util.ActivityFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;


//@Configuration
//@EnableScheduling
@Slf4j
public class ViewCountSchedule {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ActivityMapper activityMapper;

    @Scheduled(cron = "*/30 * * * * ?")
    private void myTasks() {
//        log.info("<<<<<<<<<<<view_count开始更新>>>>>>>>>>>" + LocalDateTime.now());
//        redisTemplate.delete(CommonString.LIST_ACTIVITIES.getName());
//        List<Activity> activities = activityMapper.listAllActivityTitle(new PageActivityReq());
//        activities = ActivityFilter.filter(activities);
//        redisTemplate.opsForList().rightPushAll(CommonString.LIST_ACTIVITIES.getName(), activities);
//        log.info("<<<<<<<<<<<view_count更新完成>>>>>>>>>>>" + LocalDateTime.now());
    }
}