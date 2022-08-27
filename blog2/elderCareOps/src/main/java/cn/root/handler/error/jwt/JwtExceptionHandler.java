package cn.root.handler.error.jwt;

import cn.root.common.ResponseResult;
import cn.root.common.error.jwt.ErrorJwtEnum;
import cn.root.common.error.jwt.JwtExpiredException;
import cn.root.common.error.jwt.JwtNotCorrectException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
@Order(1)
public class JwtExceptionHandler {

    @ExceptionHandler(JwtNotCorrectException.class)
    @ResponseBody
    public ResponseResult doException(JwtNotCorrectException ex) {
        log.error(ex.getMessage() + "~~~~~~~~~~~~~~~~~~~~~>>>>>Jwt无法解析");
        return ResponseResult.error(ErrorJwtEnum.JWT_MALFORMED);
    }

    @ExceptionHandler(JwtExpiredException.class)
    @ResponseBody
    public ResponseResult doException(JwtExpiredException ex) {
        log.error(ex.getMessage() + "~~~~~~~~~~~~~~~~~~~~~>>>>>Jwt已经过期");
        return ResponseResult.error(ErrorJwtEnum.JWT_HAS_EXPIRED);
    }

}
