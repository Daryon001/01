package com.hixtrip.sample.app.service;

import com.hixtrip.sample.app.api.PaymentCallback;
import com.hixtrip.sample.client.sample.vo.SampleVO;

import java.math.BigDecimal;

// PaymentSuccessStrategy.java - 支付成功策略
public class PaymentSuccessImpl implements PaymentCallback {
    @Override
    public SampleVO processPayment(String orderId, String payStatus) {
        // 处理支付成功逻辑

        return  SampleVO.builder().msg("success").code("200").msg("支付成功").build();
    }
}