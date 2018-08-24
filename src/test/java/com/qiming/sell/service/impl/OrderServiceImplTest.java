package com.qiming.sell.service.impl;

import com.qiming.sell.dataobject.OrderDetail;
import com.qiming.sell.dto.OrderDTO;
import com.qiming.sell.enums.OrderStatusEnum;
import com.qiming.sell.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuqiming
 * @date 2018/8/22
 * @email liuqiminggood@gmail.com
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "100100100";
    private final String ORDER_ID = "1534948918786342553";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("刘启明");
        orderDTO.setBuyerPhone("13166229325");
        orderDTO.setBuyerAddress("湖南省长沙市岳麓区宜居莱茵城");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetails = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1");
        o1.setProductQuantity(2);
        orderDetails.add(o1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("2");
        o2.setProductQuantity(2);
        orderDetails.add(o2);

        orderDTO.setOrderDetailList(orderDetails);
        OrderDTO result = orderService.create(orderDTO);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findList() {
        PageRequest request = new PageRequest(0, 2);
        Page<OrderDTO> orderDTOS = orderService.findList(BUYER_OPENID, request);
        Assert.assertNotNull(orderDTOS);
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }

    public char[] reverse(char[] chars, int start, int end) {   // " hello world "
        if (chars == null || chars.length == 0 || start < 0 || end >= chars.length || start >= end) {
            return chars;
        }
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
        return chars;
    }

    public String solution(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        int length = str.length();
        //第一步，翻转所有字符串
        char[] chars = reverse(str.toCharArray(), 0, str.length());
        //第二部，翻转每个单词
        int start = 0, end = 0;
        while (start < length) {
            if (chars[start] == ' ') {
                start++;
                end++;
            } else if (end == length || chars[end] == ' ') {
                chars = reverse(chars, start, end - 1);
                start = end++;
            } else {
                end++;
            }
        }
        return new String(chars);
    }
}