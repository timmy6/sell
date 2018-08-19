package com.qiming.sell.service;

import com.qiming.sell.DataObject.ProductInfo;

import java.util.List;

/**
 * @author liuqiming
 * @date 2018/8/19
 * @email liuqiminggood@gmail.com
 * @Description
 */
public interface ProductInfoService {
    ProductInfo findOne(String productId);

    List<ProductInfo> findAll();

    List<ProductInfo> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductInfo save(ProductInfo productInfo);
}
