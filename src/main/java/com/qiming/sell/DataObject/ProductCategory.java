package com.qiming.sell.DataObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author liuqiming
 * @date 2018/8/18
 * @email liuqiminggood@gmail.com
 * @Description
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {

    /**
     * 类目id
     */
    @Id
    @GeneratedValue
    private Integer categoryId;

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 类目编号
     */
    private Integer categoryType;
}