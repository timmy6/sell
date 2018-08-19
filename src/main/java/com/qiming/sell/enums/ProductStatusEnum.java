package com.qiming.sell.enums;

import lombok.Getter;

/**
 * @author liuqiming
 * @date 2018/8/19
 * @email liuqiminggood@gmail.com
 * @Description
 */
@Getter
public enum ProductStatusEnum {
    DOWN(0, "下架"),
    UP(1, "上架"),;
    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
