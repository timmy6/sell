package com.qiming.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qiming.sell.dataobject.OrderDetail;
import com.qiming.sell.dto.OrderDTO;
import com.qiming.sell.enums.ResultEnum;
import com.qiming.sell.exception.SellException;
import com.qiming.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author liuqiming
 * @date 2018/8/24
 * @email liuqiminggood@gmail.com
 * @Description
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetails;
        try {
            orderDetails = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("【对象转换错误】, string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }
}
