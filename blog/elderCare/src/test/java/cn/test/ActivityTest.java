package cn.test;

import cn.root.Application;
import cn.root.bean.req.activity.PageActivityReq;
import cn.root.service.impl.ActivityServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class ActivityTest {

    @Autowired
    private ActivityServiceImpl activityService;

    @Test
    public void pageActivityTest(){
        PageActivityReq activityReq = new PageActivityReq();
        activityService.pageActivity(activityReq);
    }

}
