package com.hixtrip.sample.app.api;

import com.hixtrip.sample.client.order.dto.CommandOderCreateDTO;
import com.hixtrip.sample.client.order.dto.CommandPayDTO;
import com.hixtrip.sample.client.sample.vo.SampleVO;

/**
 * 订单的service层
 */
public interface OrderService {
    SampleVO order(CommandOderCreateDTO commandOderCreateDTO);


    SampleVO callBack(CommandPayDTO commandPayDTO);
}
