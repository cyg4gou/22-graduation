package cn.root.common.error.requestHeader;

import cn.root.common.error.BaseError;

public enum ErrorRequestHeaderEnum implements BaseError {

    ID_NOT_EXIST(1000,"FRONT END-请求头access-id不存在"),
    TOKEN_NOT_EXIST(1001,"FRONT END-请求头access-token不存在"),
    ;

    private Integer errorCode;
    private String errorMessage;
    ErrorRequestHeaderEnum(Integer errorCode, String errorMessage){
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
