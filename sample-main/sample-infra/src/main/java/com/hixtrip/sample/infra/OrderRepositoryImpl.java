package com.hixtrip.sample.infra;


import com.hixtrip.sample.domain.order.model.Order;
import com.hixtrip.sample.domain.order.repository.OrderRepository;
import com.hixtrip.sample.domain.pay.model.CommandPay;
import com.hixtrip.sample.infra.db.convertor.OrderDOConvertor;
import com.hixtrip.sample.infra.db.convertor.SampleDOConvertor;
import com.hixtrip.sample.infra.db.dataobject.OrderDO;
import com.hixtrip.sample.infra.db.mapper.OrderMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;


/**
 * infra层是domain定义的接口具体的实现
 */
@Component
public class OrderRepositoryImpl implements OrderRepository {


    @Autowired
    private OrderMapper orderMapper;



    @Override
    public boolean createOrder(Order order) {
        return orderMapper.insert(new OrderDO(order)) > 0 ;
    }

    @Override
    public boolean orderPaySuccess(CommandPay commandPay) {
        OrderDO orderDO = orderMapper.selectById(commandPay.getOrderId());
        orderDO.setPayStatus("1");
        orderDO.setUpdateTime(LocalDateTime.now());
        return orderMapper.updateById(orderDO) > 0;
    }

    @Override
    public boolean orderPayFail(CommandPay commandPay) {
        OrderDO orderDO = orderMapper.selectById(commandPay.getOrderId());
        orderDO.setPayStatus("2");
        orderDO.setUpdateTime(LocalDateTime.now());
        return orderMapper.updateById(orderDO) > 0;
    }

    @Override
    public Order selectByMyOrder(String orderId) {
        OrderDO orderDO = orderMapper.selectById(orderId);
        return OrderDOConvertor.INSTANCE.doToDomain(orderDO);
    }
    @Override
    public Order selectByMyOrder(String orderId, Date date) {
        // todo 订单进行分表，查询时，带上创建时间
        // Order orderDO = shardingOrderMapper.selectByIdAndCreateTime(orderId,date);
        return null;
    }
}
