package cn.root.handler.error.global;

import cn.root.common.ResponseCode;
import cn.root.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
@Order
public class AllExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult doException(Exception ex) {
        ex.printStackTrace();
        log.error("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~>>>>系统异常");
        return ResponseResult.fail(ResponseCode.SYSTEM_ERROR);
    }


}