package com.qiming.sell.service;

import com.lly835.bestpay.model.PayResponse;
import com.qiming.sell.dto.OrderDTO;

/**
 * @author liuqiming
 * @date 2018/8/26
 * @email liuqiminggood@gmail.com
 * @Description
 */
public interface PayService {
    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);
}
