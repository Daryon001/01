package com.hixtrip.sample.infra;

import com.hixtrip.sample.domain.inventory.model.Inventory;
import com.hixtrip.sample.domain.inventory.repository.InventoryRepository;
import com.hixtrip.sample.infra.util.JsonUtil;
import com.hixtrip.sample.infra.util.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * infra层是domain定义的接口具体的实现
 */
@Component
public class InventoryRepositoryImpl implements InventoryRepository {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisLock redisLock;


    @Override
    public Inventory getInventoryBySkuId(String skuId) {
        Object o = redisTemplate.opsForValue().get("getInventoryBySkuId: " + skuId);
        if (o != null){
            return  JsonUtil.toBean(String.valueOf(o), Inventory.class);
        }
        return null;
    }

    @Override
    public Boolean changeInventory(String skuId, Long sellableQuantity, Long withholdingQuantity, Long occupiedQuantity) {
        boolean lock = redisLock.lock(skuId, "1", 3000L);
        if (lock){
            Inventory inventory = getInventoryBySkuId(skuId);
            BigDecimal decimal = new BigDecimal(sellableQuantity);
            BigDecimal decimal1 = new BigDecimal(occupiedQuantity);
            inventory.setSellableQuantity(decimal.subtract(decimal1).intValue());
            redisTemplate.opsForValue().set("getInventoryBySkuId: " + skuId, JsonUtil.toJson(inventory));
        }
        return false;
    }
}
