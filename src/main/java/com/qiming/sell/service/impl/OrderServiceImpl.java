package com.qiming.sell.service.impl;

import com.qiming.sell.converter.OrderMaster2OrderDTOConverter;
import com.qiming.sell.dataobject.OrderDetail;
import com.qiming.sell.dataobject.OrderMaster;
import com.qiming.sell.dataobject.ProductInfo;
import com.qiming.sell.dto.CartDTO;
import com.qiming.sell.dto.OrderDTO;
import com.qiming.sell.enums.OrderStatusEnum;
import com.qiming.sell.enums.PayStatusEnum;
import com.qiming.sell.enums.ResultEnum;
import com.qiming.sell.exception.SellException;
import com.qiming.sell.repository.OrderDetailRepository;
import com.qiming.sell.repository.OrderMasterRepository;
import com.qiming.sell.service.OrderService;
import com.qiming.sell.service.ProductService;
import com.qiming.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuqiming
 * @date 2018/8/21
 * @email liuqiminggood@gmail.com
 * @Description
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional  //事务回滚
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        //1.查询商品(数量，价格)
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算订单总价    //商品单价 x 商品数量
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //3.订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail); //属性拷贝:将productInfo的属性，拷贝到orderDetail
            orderDetailRepository.save(orderDetail);
        }
        //3.写入订单数据库 OrderMaster和OrderDetail
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);    //属性拷贝，注意：null也会拷贝过去
        orderDTO.setOrderId(orderId);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMasterRepository.save(orderMaster);

        //4.扣库存
        List<CartDTO> cartDTOS = orderDTO.getOrderDetailList().stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(cartDTOS);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
        if (orderDetails == null) {
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasters = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);//不需要查详情
        List<OrderDTO> orderDTOS = OrderMaster2OrderDTOConverter.convert(orderMasters.getContent());
        return new PageImpl<OrderDTO>(orderDTOS, pageable, orderMasters.getSize());
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderDTO.getOrderId());
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        if (orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确 orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        orderMasterRepository.save(orderMaster);
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        OrderMaster orderMaster = orderMasterRepository.findOne(orderDTO.getOrderId());
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        OrderMaster orderMaster = orderMasterRepository.findOne(orderDTO.getOrderId());
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if (result == null) {
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}