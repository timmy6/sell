package com.qiming.sell.DataObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

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
    @Id
    private String productId;

    private String productName;

    private String productPrice;    //TODO 数据库的价格对应的类型是？

    private int productStock;

    private String productDescription;

    private String productIcon;

    private int categoryType;

    public ProductInfo() {
    }

    public ProductInfo(String productId, String productName, String productPrice, int productStock, String productDescription, String productIcon, int categoryType) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productDescription = productDescription;
        this.productIcon = productIcon;
        this.categoryType = categoryType;
    }
}