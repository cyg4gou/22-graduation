package cn.root.common;

public enum ResponseCode {

    REQUEST_SUCCESS(200,"请求成功"),

    SYSTEM_ERROR(1000,"系统异常"),

    PARAMS_ERROR(10001, "参数有误"),
    ACCOUNT_PWD_NOT_EXIST(10002, "用户名或密码不存在"),
    ACCOUNT_LOGIN(10003, "账号已经登录"),
    ACCOUNT_EXIST(10004, "账号已存在"),
    ACCOUNT_NOT_EXIST(10005, "账号不存在"),

    TOKEN_EXPIRED(2000,"token已经过期"),
    TOKEN_NOT_EXIST(20001,"token不存在"),

    LOGIN_NOT_CORRECT(3000,"登录状态不对"),

    NO_PERMISSION(70001, "无访问权限"),
    SESSION_TIME_OUT(90001, "会话超时"),
    NO_LOGIN(90002, "未登录"),
    ;

    private int code;
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

}
