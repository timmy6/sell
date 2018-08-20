package com.qiming.sell.service.impl;

import com.qiming.sell.dataobject.ProductInfo;
import com.qiming.sell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author liuqiming
 * @date 2018/8/19
 * @email liuqiminggood@gmail.com
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    ProductServiceImpl productInfoService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productInfoService.findOne("1");
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductInfo> productInfos = productInfoService.findByCategoryTypeIn(Arrays.asList(1));
        Assert.assertNotEquals(0, productInfos.size());
    }

    @Test
    public void save() {
        ProductInfo productInfo = productInfoService.findOne("1");
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findProductInfoByStatus() {
        List<ProductInfo> productInfos = productInfoService.findProductInfoByStatus(ProductStatusEnum.UP.getCode());
        Assert.assertNotEquals(0, productInfos);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfos = productInfoService.findProductInfoByStatus(ProductStatusEnum.UP.getCode());
        Assert.assertNotEquals(0, productInfos.size());
    }

    @Test
    public void findPageAll() {
        PageRequest request = new PageRequest(0, 2);
        Page<ProductInfo> productInfos = productInfoService.findAll(request);
        Assert.assertNotEquals(0, productInfos);
    }
}