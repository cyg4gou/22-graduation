package cn.root.common.error.jwt;

import cn.root.common.error.BaseError;

public enum ErrorJwtEnum implements BaseError {
    JWT_HAS_EXPIRED(2000,"FRONT END-登录信息已经过期"),
    JWT_MALFORMED(2010,"FRONT END-jwt异常:格式不对，无法解析")
    ;

    private Integer errorCode;
    private String errorMessage;
    ErrorJwtEnum(Integer errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }


    @Override
    public Integer getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
