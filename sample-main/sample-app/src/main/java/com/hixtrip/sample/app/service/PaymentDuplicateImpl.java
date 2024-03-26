package com.hixtrip.sample.app.service;

import com.hixtrip.sample.app.api.PaymentCallback;
import com.hixtrip.sample.client.sample.vo.SampleVO;

import java.math.BigDecimal;

public class PaymentDuplicateImpl implements PaymentCallback {
    @Override
    public SampleVO processPayment(String orderId, String payStatus) {
        // 处理重复支付的逻辑，例如记录重复支付的日志等
        // todo 查询数据库订单状态是否是待支付，如果不是，重复支付
        return  SampleVO.builder().msg("error").code("202").msg("重复支付").build();
    }
}

