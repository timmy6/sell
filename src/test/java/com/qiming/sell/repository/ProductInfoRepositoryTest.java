package com.qiming.sell.repository;

import com.qiming.sell.DataObject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ProductInfoRepositoryTest {

    Logger logger = LoggerFactory.getLogger(ProductInfoRepositoryTest.class);

    @Autowired
    ProductInfoRepository productInfoRepository;

    @Test
    public void findProduct() {
        ProductInfo productInfo = productInfoRepository.findOne("1");
        Assert.assertNotNull(productInfo);
    }

    //    @Transactional  //不污染数据库
    @Test
    public void addProduct() {
        ProductInfo productInfo = new ProductInfo("2", "菠萝", "5.00", 10,
                "这是菠萝", "http://www.baidu.com", 4);
        productInfoRepository.save(productInfo);
    }

    @Test
    public void modifyProduct() {
        ProductInfo productInfo = productInfoRepository.findOne("1");
        productInfo.setProductIcon("www.jd.com");
        productInfoRepository.save(productInfo);
    }

    @Test
    public void findProductByCategoryIn() {
        List<ProductInfo> productInfos = productInfoRepository.findProductInfoByCategoryTypeIn(Arrays.asList(1));
        Assert.assertNotEquals(0, productInfos);
    }
}