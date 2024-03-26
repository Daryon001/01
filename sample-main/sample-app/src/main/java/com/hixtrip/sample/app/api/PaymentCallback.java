package com.hixtrip.sample.app.api;

import com.hixtrip.sample.client.sample.vo.SampleVO;

public interface PaymentCallback {
    SampleVO processPayment(String orderId, String payStatus);
}