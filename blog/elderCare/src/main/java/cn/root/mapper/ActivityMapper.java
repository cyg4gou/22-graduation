package cn.root.mapper;

import cn.root.bean.entity.Activity;
import cn.root.bean.req.activity.PageActivityReq;
import cn.root.bean.resp.activity.ActivityResp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {

    List<Activity> listAllActivityTitle(PageActivityReq pageActivityReq);

}
