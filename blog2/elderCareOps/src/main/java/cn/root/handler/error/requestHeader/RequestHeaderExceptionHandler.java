package cn.root.handler.error.requestHeader;

import cn.root.common.ResponseResult;
import cn.root.common.error.requestHeader.ErrorRequestHeaderEnum;
import cn.root.common.error.requestHeader.IdNotExistException;
import cn.root.common.error.requestHeader.TokenNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
@Order(1)
public class RequestHeaderExceptionHandler {

    @ExceptionHandler(IdNotExistException.class)
    @ResponseBody
    public ResponseResult doException(IdNotExistException ex) {
        log.error(ex.getMessage() + "~~~~~~~~~~~~~~~~~~~~~>>>>>id不存在");
        return ResponseResult.error(ErrorRequestHeaderEnum.ID_NOT_EXIST);
    }

    @ExceptionHandler(TokenNotExistException.class)
    @ResponseBody
    public ResponseResult doException(TokenNotExistException ex) {
        log.error(ex.getMessage() + "~~~~~~~~~~~~~~~~~~~~~>>>>>token不存在");
        return ResponseResult.error(ErrorRequestHeaderEnum.TOKEN_NOT_EXIST);
    }

}
