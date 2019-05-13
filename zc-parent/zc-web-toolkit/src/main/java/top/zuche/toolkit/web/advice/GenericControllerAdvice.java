package top.zuche.toolkit.web.advice;

import com.alibaba.dubbo.rpc.RpcException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import top.zuche.common.web.resp.ResponseCode;
import top.zuche.common.web.resp.ResponseData;

/**
 * @author lzx
 * @date 2019/5/13 上午10:09
 */
@ControllerAdvice
public class GenericControllerAdvice {

    /**
     * 在所有RequestMapping 执行之前，执行数据绑定
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {

    }

    @SuppressWarnings("unchecked")
    @ResponseBody
    @ExceptionHandler(value = RpcException.class)
    public ResponseData<Void> errHandler(RpcException rpcError) {
        if (rpcError.isBiz()) {
            return ResponseData.error(ResponseCode.SERVICE_ERROR);
        } else if (rpcError.isForbidded()) {
            return ResponseData.error(ResponseCode.ACCESS_DENIED);
        } else if (rpcError.isNetwork()) {
            return ResponseData.error(ResponseCode.NETWORK_ERROR);
        } else if (rpcError.isTimeout()) {
            return ResponseData.error(ResponseCode.SERVICE_TIMEOUT);
        } else if (rpcError.isSerialization()) {
            return ResponseData.error(ResponseCode.SERIALIZATION_ERROR);
        }
        return ResponseData.error(ResponseCode.SERVICE_ERROR);
    }
}
