package com.qiming.sell.controller;

import com.qiming.sell.dto.OrderDTO;
import com.qiming.sell.service.OrderService;
import com.qiming.sell.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author liuqiming
 * @date 2018/8/26
 * @email liuqiminggood@gmail.com
 * @Description
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId, @RequestParam("returnUrl") String returnUrl, Map<String, Object> map) {
//        //1.查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
//        if (orderDTO == null) {
//            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
//        }
//        //2.发起支付
//        PayResponse pay = payService.create(orderDTO);
        map.put("orderDTO", orderDTO);
        map.put("returnUrl", returnUrl);
        return new ModelAndView("pay/create", map);
    }


    /**
     * 微信异步通知，会一直通知，处理完成后，需要给微信发个回调，停止通知
     *
     * @param notifyData
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestParam String notifyData) {
        payService.notify(notifyData);
        return new ModelAndView("pay/success");
    }
}