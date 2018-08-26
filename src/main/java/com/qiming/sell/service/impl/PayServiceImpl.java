package com.qiming.sell.service.impl;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.qiming.sell.dto.OrderDTO;
import com.qiming.sell.service.PayService;
import org.springframework.stereotype.Service;

/**
 * @author liuqiming
 * @date 2018/8/26
 * @email liuqiminggood@gmail.com
 * @Description
 */
@Service
public class PayServiceImpl implements PayService {

    @Override
    public void create(OrderDTO orderDTO) {

        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        bestPayService.setWxPayH5Config();
    }
}