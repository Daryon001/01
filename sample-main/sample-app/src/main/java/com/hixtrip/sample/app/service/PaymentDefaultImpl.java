package com.hixtrip.sample.app.service;

import com.hixtrip.sample.app.api.PaymentCallback;
import com.hixtrip.sample.client.sample.vo.SampleVO;

public class PaymentDefaultImpl implements PaymentCallback {
    @Override
    public SampleVO processPayment(String orderId, String payStatus) {
        // 默认处理
        return  SampleVO.builder().msg("error").code("204").msg("网络异常").build();
    }
}

