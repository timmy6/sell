package com.qiming.sell.enums;

import lombok.Getter;

/**
 * @author liuqiming
 * @date 2018/8/20
 * @email liuqiminggood@gmail.com
 * @Description 订单状态
 */
@Getter
public enum OrderStatusEnum implements CodeEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),;

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}