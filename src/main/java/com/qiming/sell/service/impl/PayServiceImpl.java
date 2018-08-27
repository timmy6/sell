package com.qiming.sell.service.impl;

import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.qiming.sell.dto.OrderDTO;
import com.qiming.sell.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuqiming
 * @date 2018/8/26
 * @email liuqiminggood@gmail.com
 * @Description
 */
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Override
    public void create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        bestPayService.pay(payRequest);
    }
}