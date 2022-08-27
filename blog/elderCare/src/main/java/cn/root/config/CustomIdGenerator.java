package cn.root.config;

import cn.root.bean.entity.ViewCount;
import cn.root.mapper.ViewCountMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
//@Component
public class CustomIdGenerator implements IdentifierGenerator {

    private final AtomicLong al = new AtomicLong(1);

    @Override
    public Long nextId(Object entity) {
        //可以将当前传入的class全类名来作为bizKey,或者提取参数来生成bizKey进行分布式Id调用生成.
        String bizKey = entity.getClass().getName();
        log.info("bizKey:{}", bizKey);
        MetaObject metaObject = SystemMetaObject.forObject(entity);
        String salt = null;
        if(!bizKey.contains("ViewCount")){
            salt = (String) metaObject.getValue("salt");
        }
        final long id = al.getAndAdd(1);
        log.info("为{}生成主键值->:{}", salt, id);
        return id;
    }
}