package cn.root.common.error.jwt;

import cn.root.common.error.requestHeader.ErrorRequestHeaderEnum;

public class JwtExpiredException extends RuntimeException{

    protected Integer errorCode;
    protected String errorMessage;

    public JwtExpiredException(){
        this.errorCode = ErrorRequestHeaderEnum.ID_NOT_EXIST.getErrorCode();
        this.errorMessage = ErrorRequestHeaderEnum.ID_NOT_EXIST.getErrorMessage();
    }
}
