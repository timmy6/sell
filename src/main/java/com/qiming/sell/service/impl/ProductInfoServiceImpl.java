package com.qiming.sell.service.impl;

import com.qiming.sell.DataObject.ProductInfo;
import com.qiming.sell.repository.ProductInfoRepository;
import com.qiming.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuqiming
 * @date 2018/8/19
 * @email liuqiminggood@gmail.com
 * @Description
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findAll() {
        return productInfoRepository.findAll();
    }

    @Override
    public List<ProductInfo> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productInfoRepository.findProductInfoByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }
}