package com.qiming.sell.service.impl;

import com.qiming.sell.DataObject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class ProductInfoServiceImplTest {

    @Autowired
    ProductInfoServiceImpl productInfoService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productInfoService.findOne("1");
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findAll() {
        List<ProductInfo> productInfos = productInfoService.findAll();
        Assert.assertNotEquals(0, productInfos.size());
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
}