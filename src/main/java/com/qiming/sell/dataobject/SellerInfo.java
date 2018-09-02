package com.qiming.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author liuqiming
 * @date 2018/9/2
 * @email liuqiminggood@gmail.com
 * @Description 卖家信息表
 */
@Data
@Entity
public class SellerInfo {
    @Id
    private String sellerId;

    private String username;

    private String password;
}