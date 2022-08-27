package cn.root.service;


import cn.root.bean.entity.User;
import cn.root.bean.req.user.LoginReq;
import cn.root.bean.req.user.RegistryReq;
import cn.root.bean.req.user.UserUpdatePasswordReq;
import cn.root.bean.resp.activity.PageActivityResp;
import cn.root.bean.resp.user.UserLoginResp;
import cn.root.bean.resp.user.UserPublicResp;
import cn.root.common.ResponseResult;
import cn.root.plus.Logind;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IUserService extends IService<User> {

    ResponseResult<Integer> register(RegistryReq registryReq);

    ResponseResult<Integer> updatePassword(UserUpdatePasswordReq userUpdatePasswordReq);

     ResponseResult<UserLoginResp> login(LoginReq loginReq);

    ResponseResult<Integer> logout();

    @Deprecated
    ResponseResult<PageActivityResp> viewOther(String id);

    ResponseResult<Boolean> checkOldPassword(String oldPassword);
}
