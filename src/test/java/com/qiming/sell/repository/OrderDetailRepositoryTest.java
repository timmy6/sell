package com.qiming.sell.repository;

import com.qiming.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author liuqiming
 * @date 2018/8/20
 * @email liuqiminggood@gmail.com
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    /**
     *
     */
    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId("123456");
        Assert.assertNotEquals(0, orderDetails);
    }

    @Test
    public void save() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("12345667");
        orderDetail.setOrderId("123456");
        orderDetail.setProductId("12312312312");
        orderDetail.setProductIcon("http://www.baidu.com");
        orderDetail.setProductName("魅族th Plus16");
        orderDetail.setProductPrice(new BigDecimal(2.1));
        orderDetail.setProductQuantity(20);

        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(result);
    }
}