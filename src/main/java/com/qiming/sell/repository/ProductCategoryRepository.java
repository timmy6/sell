package com.qiming.sell.repository;

import com.qiming.sell.DataObject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author liuqiming
 * @date 2018/8/18
 * @email liuqiminggood@gmail.com
 * @Description
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

}