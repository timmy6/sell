package com.qiming.sell.enums;

import lombok.Getter;

/**
 * @author liuqiming
 * @date 2018/8/20
 * @email liuqiminggood@gmail.com
 * @Description 支付状态
 */
@Getter
public enum PayStatusEnum implements CodeEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),;

    private Integer code;
    private String msg;

    PayStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
