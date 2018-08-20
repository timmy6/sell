package com.qiming.sell.service;

import com.qiming.sell.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author liuqiming
 * @date 2018/8/19
 * @email liuqiminggood@gmail.com
 * @Description
 */
public interface ProductService {
    ProductInfo findOne(String productId);

    Page<ProductInfo> findAll(Pageable pageable);

    List<ProductInfo> findUpAll();

    List<ProductInfo> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductInfo save(ProductInfo productInfo);

    List<ProductInfo> findProductInfoByStatus(int status);


    //加库存
    //减库存
}