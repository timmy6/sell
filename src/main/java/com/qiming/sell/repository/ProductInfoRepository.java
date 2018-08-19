package com.qiming.sell.repository;

import com.qiming.sell.DataObject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author liuqiming
 * @date 2018/8/19
 * @email liuqiminggood@gmail.com
 * @Description
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    List<ProductInfo> findProductInfoByCategoryTypeIn(List<Integer> categoryTypeList);
}