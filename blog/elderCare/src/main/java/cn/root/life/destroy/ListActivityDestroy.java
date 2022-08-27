package cn.root.life.destroy;

import cn.root.common.CommonString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

//@Component
//@Slf4j
//public class ListActivityDestroy {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @PreDestroy
//    public void destroy() {
//
//        System.out.println("正在销毁>>>>>>>>>>>>>>>>List_activity");
//        redisTemplate.opsForList().getOperations().delete(CommonString.LIST_ACTIVITIES.getName());
//        System.out.println("<<<<<<<<<<<<<<<<List_activity销毁成功");
//
//    }
//}


