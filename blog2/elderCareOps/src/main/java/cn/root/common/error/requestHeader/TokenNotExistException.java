package cn.root.common.error.requestHeader;

public class TokenNotExistException extends RuntimeException{

    protected Integer errorCode;
    protected String errorMessage;

    public TokenNotExistException(){
        this.errorCode = ErrorRequestHeaderEnum.ID_NOT_EXIST.getErrorCode();
        this.errorMessage = ErrorRequestHeaderEnum.ID_NOT_EXIST.getErrorMessage();
    }
}
