package com.qiming.sell.enums;

import lombok.Getter;

/**
 * @author liuqiming
 * @date 2018/8/21
 * @email liuqiminggood@gmail.com
 * @Description 异常枚举
 */
@Getter
public enum ResultEnum {
    PARAM_ERROR(1, "参数不正确"),
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "库存不正确"),
    ORDER_NOT_EXIST(12, "订单不存在"),
    ORDER_DETAIL_NOT_EXIST(13, "订单详情不存在"),
    ORDER_STATUS_ERROR(14, "订单状态错误"),
    ORDER_UPDATE_FAIL(15, "更新失败"),
    ORDER_PAY_STATUS_ERROR(16, "订单支付状态不正确"),
    CART_EMPTY(18, "购物车为空");

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
