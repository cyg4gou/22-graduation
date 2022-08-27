package cn.root.controller;

import cn.root.bean.req.user.LoginReq;
import cn.root.bean.req.user.RegistryReq;
import cn.root.bean.req.user.UserUpdatePasswordReq;
import cn.root.bean.resp.activity.PageActivityResp;
import cn.root.bean.resp.user.UserLoginResp;
import cn.root.common.ResponseResult;
import cn.root.plus.Logind;
import cn.root.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(tags = {"用户"})
public class UserController {

    @Autowired
    private IUserService iUserService;

    @PostMapping(value = "/registry")
    @ApiOperation(value = "注册用户")
    @Logind(required = false)
    public ResponseResult<Integer> register(@RequestBody RegistryReq registryReq) {
        return iUserService.register(registryReq);
    }

    @PostMapping(value = "/updatePassword")
    @ApiOperation(value = "更改密码")
    @Logind()
    public ResponseResult<Integer> updatePassword(@RequestBody UserUpdatePasswordReq userUpdatePasswordReq) {
        return iUserService.updatePassword(userUpdatePasswordReq);
    }

    @PostMapping(value = "/checkOldPassword")
    @ApiOperation(value = "确认旧密码")
    @Logind()
    public ResponseResult<Boolean> checkOldPassword(@RequestBody UserUpdatePasswordReq userUpdatePasswordReq) {
        return iUserService.checkOldPassword(userUpdatePasswordReq.getOldPassword());
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "登录")
    @Logind(required = false)
    public ResponseResult<UserLoginResp> login(@RequestBody LoginReq loginReq) {
        return iUserService.login(loginReq);
    }

    @PostMapping(value = "/logout")
    @ApiOperation(value = "退出登录")
    @Logind()
    public ResponseResult<Integer> logout() {
        return iUserService.logout();
    }

    @GetMapping(value = "/view/{id}")
    @ApiOperation(value = "查看其它用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "查看其它用户信息的用户id", required = true),
    })
    @Logind
    public ResponseResult<PageActivityResp> viewOther(@PathVariable String id) {
        return iUserService.viewOther(id);
    }

}
