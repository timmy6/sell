package com.qiming.sell.dto;

import lombok.Data;

/**
 * @author liuqiming
 * @date 2018/8/22
 * @email liuqiminggood@gmail.com
 * @Description 购物车
 */
@Data
public class CartDTO {

    /**
     * 商品Id
     */
    private String productId;

    /**
     * 库存
     */
    private Integer productQuantity;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}