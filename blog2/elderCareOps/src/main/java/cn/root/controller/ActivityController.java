package cn.root.controller;

import cn.root.bean.req.activity.PageActivityReq;
import cn.root.bean.req.activity.PublishReq;
import cn.root.bean.resp.activity.ActivityBodyResp;
import cn.root.bean.resp.activity.PageActivityResp;
import cn.root.common.ResponseResult;
import cn.root.plus.Logind;
import cn.root.service.IActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/activity")
@Api(tags = {"活动"})
public class ActivityController {

    @Autowired
    private IActivityService activityService;

    @PostMapping(value = "/page")
    @ApiOperation(value = "活动总览")
    @Logind(required = false)
    public ResponseResult<PageActivityResp> pageActivity(@RequestBody PageActivityReq pageActivityReq){
        return activityService.pageActivity(pageActivityReq);
    }

    @PostMapping(value = "/publish")
    @ApiOperation(value = "发布活动")
    @Logind
    public ResponseResult<Integer> publish(@RequestBody PublishReq publishReq){
        return activityService.publish(publishReq);
    }

    @GetMapping(value = "/viewContent/{activityId}")
    @ApiOperation(value = "查看活动体内容")
    @Logind
    public ResponseResult<ActivityBodyResp> viewContent(@PathVariable String activityId){
        return activityService.viewContent(activityId);
    }

    @GetMapping(value = "/deleteAll")
    @ApiOperation(value = "删除活动列表")
    @Logind
    public ResponseResult<Integer> delete(String[] activitiesToDelete){
        return activityService.deleteAll(activitiesToDelete);
    }

    @GetMapping(value = "/delete/{activityId}")
    @ApiOperation(value = "删除活动")
    @Logind
    public ResponseResult<Integer> delete(@PathVariable String activityId){
        return activityService.delete(activityId);
    }
}
