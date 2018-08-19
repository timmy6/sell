package com.qiming.sell.service.impl;

import com.qiming.sell.dataObject.ProductInfo;
import com.qiming.sell.enums.ProductStatusEnum;
import com.qiming.sell.repository.ProductInfoRepository;
import com.qiming.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuqiming
 * @date 2018/8/19
 * @email liuqiminggood@gmail.com
 * @Description
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productInfoRepository.findProductInfoByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public List<ProductInfo> findProductInfoByStatus(int status) {
        return productInfoRepository.findProductInfoByProductStatus(status);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    /**
     * 查询在架商品
     *
     * @return
     */
    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findProductInfoByProductStatus(ProductStatusEnum.UP.getCode());
    }
}