#todo 你的建表语句,包含索引
    # 创建表可以用创建时间来分表，1个月一张表，sql语句都带上create_time
        # 查询时，如果非常频繁，可以将数据写入二级缓存，cache或者redis里
CREATE TABLE IF NOT EXISTS `order_${date}` (
                         `id` bigint AUTO_INCREMENT PRIMARY KEY,
                         `user_id` VARCHAR(32) NOT NULL,
                         `sku_id` VARCHAR(32) NOT NULL,
                         `amount` INT NOT NULL,
                         `money` DECIMAL(10, 2) NOT NULL,
                         `pay_time` DATETIME,
                         `pay_status` VARCHAR(20),
                         `del_flag` TINYINT DEFAULT 0,
                         `create_by` VARCHAR(32),
                         `create_time` DATETIME,
                         `update_by` VARCHAR(32),
                         `update_time` DATETIME,
                         INDEX `${date}_user_id_index` (`user_id`),
                         INDEX `${date}_sku_id_index` (`sku_id`),
                         INDEX `${date}_pay_time_index` (`pay_time`),
                         INDEX `${date}_pay_status_index` (`pay_status`)
);


