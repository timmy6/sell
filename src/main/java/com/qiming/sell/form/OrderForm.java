package com.qiming.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author liuqiming
 * @date 2018/8/24
 * @email liuqiminggood@gmail.com
 * @Description 订单表单验证
 */
@Data
public class OrderForm {

    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名必填")
    private String name;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号必填")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "地址必填")
    private String address;

    /**
     * 买家微信openid
     */
    @NotEmpty(message = "openid必填")
    private String openid;

    /**
     * 购物车信息
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;
}