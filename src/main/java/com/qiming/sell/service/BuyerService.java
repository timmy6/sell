package com.qiming.sell.service;

import com.qiming.sell.dto.OrderDTO;

/**
 * @author liuqiming
 * @date 2018/8/25
 * @email liuqiminggood@gmail.com
 * @Description 买家
 */
public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
