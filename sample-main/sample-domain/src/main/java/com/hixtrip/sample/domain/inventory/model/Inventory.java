package com.hixtrip.sample.domain.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * 这是领域对象的示例, 要求使用充血模型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuperBuilder(toBuilder = true)
public class Inventory {
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 单价
     */
    private BigDecimal money;

    /**
     * 可售库存
     */
    private Integer sellableQuantity;
}
