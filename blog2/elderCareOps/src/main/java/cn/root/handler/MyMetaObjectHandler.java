package cn.root.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        /*
         * user
         *      version：mybatis_plus版本号
         *
         * activity
         *      viewCount ：查看人数
         */
        this.strictInsertFill(metaObject, "version", Integer.class, 1); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "viewCount", Integer.class, 0); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "createTime"
                , Date.class
                , new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐)
    }
}