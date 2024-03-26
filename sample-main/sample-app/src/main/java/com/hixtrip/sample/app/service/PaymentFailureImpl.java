package com.hixtrip.sample.app.service;

import com.hixtrip.sample.app.api.PaymentCallback;
import com.hixtrip.sample.client.sample.vo.SampleVO;

import java.math.BigDecimal;

public class PaymentFailureImpl implements PaymentCallback {
    @Override
    public SampleVO processPayment(String orderId, String payStatus) {
        // 处理支付失败的逻辑，例如记录支付失败的日志等
        // 把库存加上
        return  SampleVO.builder().msg("error").code("201").msg("支付失败").build();
    }
}