package com.qiming.sell.controller;

import com.qiming.sell.base.BaseController;
import com.qiming.sell.common.ret.RetResult;
import com.qiming.sell.dataObject.ProductCategory;
import com.qiming.sell.dataObject.ProductInfo;
import com.qiming.sell.service.CategoryService;
import com.qiming.sell.service.ProductService;
import com.qiming.sell.vo.ProductInfoVO;
import com.qiming.sell.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuqiming
 * @date 2018/8/19
 * @email liuqiminggood@gmail.com
 * @Description
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public RetResult list() {
        //1.查询所有的上架商品
        List<ProductInfo> productInfos = productService.findUpAll();
        //2.查询类目(一次性查询)
        //2.1传统方法,for循环
//        List<Integer> categoryTypes = new ArrayList<>();
//        for (ProductInfo productInfo : productInfos) {
//            categoryTypes.add(productInfo.getCategoryType());
//        }
        //2.2精简方法 java8 lambda
        List<Integer> categoryTypes = productInfos.stream()
                .map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategories = categoryService.findByCategoryTypeIn(categoryTypes);
        //3.数据拼装
        List<ProductVO> productVOs = new ArrayList<>();
        for (ProductCategory productCategory : productCategories) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOS = new ArrayList<>();
            for (ProductInfo productInfo : productInfos) {
                if (productInfo.getCategoryType() == productCategory.getCategoryType()) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);   //BeanUtils 将 source 的属性值，copy 到目标对象的属性值上面
                    productInfoVOS.add(productInfoVO);
                }
            }
            productVO.setFoods(productInfoVOS);
            productVOs.add(productVO);
        }
        return makeOKRsp(productVOs);
    }
}