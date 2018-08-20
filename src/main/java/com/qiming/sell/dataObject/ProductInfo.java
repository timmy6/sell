package com.qiming.sell.dataObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author liuqiming
 * @date 2018/8/19
 * @email liuqiminggood@gmail.com
 * @Description 商品对象
 */
@Entity
@DynamicUpdate
@Data
public class ProductInfo {

    /**
     * 商品id
     */
    @Id
    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 价格
     */
    private BigDecimal productPrice;

    /**
     * 库存
     */
    private int productStock;

    /**
     * 描述
     */
    private String productDescription;

    /**
     * 小图
     */
    private String productIcon;

    /**
     * 类目编号
     */
    private Integer categoryType;

    /**
     * 状态：0下架/1上架
     */
    private int productStatus;

    public ProductInfo() {
    }

    public ProductInfo(String productId, String productName, BigDecimal productPrice, int productStock, String productDescription, String productIcon, Integer categoryType, int productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productDescription = productDescription;
        this.productIcon = productIcon;
        this.categoryType = categoryType;
        this.productStatus = productStatus;
    }
}