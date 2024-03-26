package com.hixtrip.sample.infra.db.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import com.hixtrip.sample.domain.order.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "order")
@SuperBuilder(toBuilder = true)
public class OrderDO {

    /**
     * 订单号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 购买人
     */
    private String userId;

    /**
     * SkuId
     */
    private String skuId;

    /**
     * 购买数量
     */
    private Integer amount;

    /**
     * 购买金额
     */
    private BigDecimal money;

    /**
     * 购买时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime payTime;

    /**
     * 支付状态
     */
    private String payStatus;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableLogic
    private Long delFlag;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    public OrderDO(Order order) {
        this.id = Long.valueOf(order.getId());
        this.userId = order.getUserId();
        this.skuId = order.getSkuId();
        this.amount = order.getAmount();
        this.money = order.getMoney();
        this.payTime = order.getPayTime();
        this.payStatus = order.getPayStatus();
        this.delFlag = order.getDelFlag();
        this.createBy = order.getCreateBy();
        this.createTime = order.getCreateTime();
        this.updateBy = order.getUpdateBy();
        this.updateTime = order.getUpdateTime();
    }
}

