package cn.root.life.init;

import cn.root.bean.entity.ViewCount;
import cn.root.common.Constant;
import cn.root.mapper.ActivityMapper;
import cn.root.mapper.ViewCountMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@Slf4j
public class ViewCountInit implements ApplicationRunner, DisposableBean {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ViewCountMapper viewCountMapper;


    @Override
    public void run(ApplicationArguments args){
        log.info("***************viewCount初始化开始*****************");

        log.info("***************viewCount初始化成功*****************");
    }

    @Override
    public void destroy() {
//        viewCountMapper.delete(new LambdaQueryWrapper<ViewCount>()
//                .eq(ViewCount::getIfDelete, Constant.notDeleted)
//                .or()
//                .eq(ViewCount::getIfDelete, Constant.deleted));
    }
}
