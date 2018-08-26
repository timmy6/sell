package com.qiming.sell.service;

import com.qiming.sell.dto.OrderDTO;

/**
 * @author liuqiming
 * @date 2018/8/26
 * @email liuqiminggood@gmail.com
 * @Description
 */
public interface PayService {
    void create(OrderDTO orderDTO);
}
