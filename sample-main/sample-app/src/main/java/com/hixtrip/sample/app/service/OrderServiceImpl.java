package com.hixtrip.sample.app.service;

import com.hixtrip.sample.app.api.OrderService;
import com.hixtrip.sample.app.api.PaymentCallback;
import com.hixtrip.sample.client.order.dto.CommandOderCreateDTO;
import com.hixtrip.sample.client.order.dto.CommandPayDTO;
import com.hixtrip.sample.client.sample.vo.SampleVO;
import com.hixtrip.sample.domain.inventory.InventoryDomainService;
import com.hixtrip.sample.domain.inventory.model.Inventory;
import com.hixtrip.sample.domain.order.OrderDomainService;
import com.hixtrip.sample.domain.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * app层负责处理request请求，调用领域服务
 */
@Component
public class OrderServiceImpl implements OrderService {


    @Autowired
    private InventoryDomainService inventoryDomainService;

    @Autowired
    private OrderDomainService orderDomainService;

    @Autowired
    private PaymentCallback paymentCallback;

    private static Map<String, PaymentCallback> CALL_BACK = new HashMap<>(16);

    static {
        CALL_BACK.put("1", new PaymentSuccessImpl());
        CALL_BACK.put("2", new PaymentFailureImpl());
        CALL_BACK.put("3", new PaymentDuplicateImpl());
        CALL_BACK.put("4", new PaymentDefaultImpl());
    }


    @Override
    public SampleVO order(CommandOderCreateDTO commandOderCreateDTO) {
        Integer number = commandOderCreateDTO.getAmount();
        //查询sku
        Inventory inventory = inventoryDomainService.getInventory(commandOderCreateDTO.getSkuId());
        if (inventory.getSellableQuantity().compareTo(number) > 0) {
            long value = number.longValue();
            //扣除库存
            Boolean aBoolean = inventoryDomainService.changeInventory(commandOderCreateDTO.getSkuId(),
                    Long.valueOf(inventory.getSellableQuantity()),
                    value,
                    value);
            if (aBoolean){
                //创建订单
                Order build = Order.builder().amount(number)
                        .money(inventory.getMoney().multiply(new BigDecimal(number)))
                        .userId(commandOderCreateDTO.getUserId())
                        .skuId(commandOderCreateDTO.getSkuId())
                        .payTime(LocalDateTime.now())
                        .payStatus("0")
                        .delFlag(0L)
                        .createBy(commandOderCreateDTO.getSkuId())
                        .createTime(LocalDateTime.now())
                        .build();
                boolean order = orderDomainService.createOrder(build);
                return order ? SampleVO.builder().msg("error").code("200").msg("创建订单成功").build():
                        SampleVO.builder().msg("error").code("201").msg("网络繁忙").build();
            }else {
                SampleVO.builder().msg("error").code("201").msg("网络繁忙").build();
            }
        }
        return  SampleVO.builder().msg("error").code("202").msg("库存不足").build();
    }

    @Override
    public SampleVO callBack(CommandPayDTO commandPayDTO) {
        Order order = orderDomainService.selectByMyOrder(commandPayDTO.getOrderId());
        if (order == null){
            return SampleVO.builder().msg("error").code("206").msg("订单异常").build();
        }
        return CALL_BACK.get(commandPayDTO.getPayStatus()).processPayment(order.getId(), commandPayDTO.getPayStatus());
    }
}
