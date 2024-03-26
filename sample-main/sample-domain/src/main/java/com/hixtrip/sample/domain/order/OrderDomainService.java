package com.hixtrip.sample.domain.order;

import com.hixtrip.sample.domain.order.model.Order;
import com.hixtrip.sample.domain.order.repository.OrderRepository;
import com.hixtrip.sample.domain.pay.model.CommandPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 订单领域服务
 * todo 只需要实现创建订单即可
 */
@Component
public class OrderDomainService {


    @Autowired
    private OrderRepository orderRepository;

    /**
     * todo 需要实现
     * 创建待付款订单
     */
    public boolean createOrder(Order order) {

        //需要你在infra实现, 自行定义出入参
        return orderRepository.createOrder(order);
    }

    /**
     * todo 需要实现
     * 待付款订单支付成功
     */
    public boolean orderPaySuccess(CommandPay commandPay) {
        //需要你在infra实现, 自行定义出入参
        return orderRepository.orderPaySuccess(commandPay);
    }

    /**
     * todo 需要实现
     * 待付款订单支付失败
     */
    public boolean orderPayFail(CommandPay commandPay) {
        //需要你在infra实现, 自行定义出入参
        return orderRepository.orderPayFail(commandPay);
    }

    public Order selectByMyOrder(String orderId) {
        return orderRepository.selectByMyOrder(orderId);
    }

    public Order selectByMyOrder(String orderId, Date date) {
        return orderRepository.selectByMyOrder(orderId);
    }
}
