package cn.root.common;

import cn.root.common.error.BaseError;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel("请求响应结果")
public class ResponseResult<T> {

    @ApiModelProperty("状态码")
    private int code;

    @ApiModelProperty("请求结果描述信息")
    private String message;

    @ApiModelProperty("请求返回的数据")
    private T data;


    public static <T> ResponseResult<T> success(T data){
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS.getCode(), ResponseCode.REQUEST_SUCCESS.getMsg(),data);
    }

    public static <T> ResponseResult<T> success(){return success(null);}

    public static <T> ResponseResult<T> fail(int code, String message){
        return new ResponseResult(code,message,null);
    }

    public static <T> ResponseResult<T> fail(ResponseCode responseCode){return fail(responseCode.getCode(), responseCode.getMsg());}

    public static <T> ResponseResult<T> error(BaseError baseError){
        return fail(baseError.getErrorCode(),baseError.getErrorMessage());
    }
}
