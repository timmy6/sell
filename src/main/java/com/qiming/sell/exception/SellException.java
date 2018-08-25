package com.qiming.sell.exception;

import com.qiming.sell.enums.ResultEnum;

/**
 * @author liuqiming
 * @date 2018/8/21
 * @email liuqiminggood@gmail.com
 * @Description
 */
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
