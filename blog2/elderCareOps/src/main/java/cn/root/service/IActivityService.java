package cn.root.service;

import cn.root.bean.entity.Activity;
import cn.root.bean.req.activity.PageActivityReq;
import cn.root.bean.req.activity.PublishReq;
import cn.root.bean.resp.activity.ActivityBodyResp;
import cn.root.bean.resp.activity.ActivityResp;
import cn.root.bean.resp.activity.PageActivityResp;
import cn.root.common.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface IActivityService extends IService<Activity> {

    ResponseResult<PageActivityResp> pageActivity(PageActivityReq pageActivityReq);

    ResponseResult<Integer> publish(PublishReq publishReq);

    ResponseResult<ActivityBodyResp> viewContent(String activityId);

    ResponseResult<Integer> deleteAll(String[] activitiesToDelete);

    ResponseResult<Integer> delete(String activityId);

    /**
     * 私有
     * @param id 用户id
     * @return ActivityResp[]
     */
    List<ActivityResp> transform(List<Activity> activities);
}
