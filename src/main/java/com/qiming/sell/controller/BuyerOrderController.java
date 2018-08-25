package com.qiming.sell.controller;

import com.qiming.sell.base.BaseController;
import com.qiming.sell.common.ret.RetResult;
import com.qiming.sell.converter.OrderForm2OrderDTOConverter;
import com.qiming.sell.dto.OrderDTO;
import com.qiming.sell.enums.ResultEnum;
import com.qiming.sell.exception.SellException;
import com.qiming.sell.form.OrderForm;
import com.qiming.sell.service.BuyerService;
import com.qiming.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuqiming
 * @date 2018/8/24
 * @email liuqiminggood@gmail.com
 * @Description
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单

    @PostMapping("/create")
    public RetResult<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】 参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());
        return makeOKRsp(map);
    }

    /**
     * 订单列表
     *
     * @return
     */
    @PostMapping("/list")
    public RetResult<OrderDTO> list(@RequestParam("openid") String openid,
                                    @RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @RequestParam(value = "size", defaultValue = "20") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageRequest);
        return makeOKRsp(orderDTOPage.getContent());
    }

    /**
     * 订单详情
     *
     * @param openid
     * @param orderId
     * @return
     */
    @GetMapping("/detail")
    public RetResult<OrderDTO> detail(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return makeOKRsp(orderDTO);
    }

    /**
     * 取消订单
     *
     * @param openid
     * @param orderId
     * @return
     */
    @PostMapping("/cancel")
    public RetResult cancel(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.cancelOrder(openid, orderId);
        return makeOKRsp();
    }
}