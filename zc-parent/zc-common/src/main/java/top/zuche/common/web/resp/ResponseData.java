package top.zuche.common.web.resp;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author lzx
 * @date 2019/5/9 下午3:09
 */
@Getter
@Setter
public class ResponseData<T> implements Serializable {

    private int code;

    private T data;

    private String message;

    private ResponseData() {}

    /**
     * 成功响应
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseData<T> ok() {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.fillWithResponseCode(ResponseCode.SUCCESS);
        return responseData;
    }

    /**
     * 成功响应
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseData<T> ok(T data) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setData(data);
        responseData.fillWithResponseCode(ResponseCode.SUCCESS);
        return responseData;
    }

    /**
     * 失败响应
     *
     * @param code
     * @param <T>
     * @return
     */
    public static <T> ResponseData error(ResponseCode code) {
        if (ResponseCode.SUCCESS == code) {
            throw new RuntimeException("Please use error code.");
        }
        ResponseData<T> responseData = new ResponseData<>();
        responseData.fillWithResponseCode(code);
        return responseData;
    }

    /**
     * 自定义 - 业务异常
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseData error(String message) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setCode(ResponseCode.BUSINESS_ERROR.code);
        responseData.setMessage(message);
        return responseData;
    }

    /**
     * 状态码填充
     * @param rc
     * @param <T>
     */
    private <T> void fillWithResponseCode(ResponseCode rc) {
        this.setCode(rc.code);
        this.setMessage(rc.message);
    }
}
