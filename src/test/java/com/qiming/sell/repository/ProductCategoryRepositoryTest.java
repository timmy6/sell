package com.qiming.sell.repository;

import com.qiming.sell.DataObject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liuqiming
 * @date 2018/8/18
 * @email liuqiminggood@gmail.com
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest() {
        ProductCategory productCategory = repository.findOne(1);
        System.out.print(productCategory.toString());
    }

    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("女神最爱榜");
        productCategory.setCategoryType(3);
        repository.save(productCategory);
    }

    @Test
    public void updateTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(2);
        productCategory.setCategoryName("男生最爱榜");
        productCategory.setCategoryType(3);
        repository.save(productCategory);
    }

    @Test
    public void update2Test() {
        ProductCategory productCategory = repository.findOne(2);
        productCategory.setCategoryType(3);
        repository.save(productCategory);
    }
}