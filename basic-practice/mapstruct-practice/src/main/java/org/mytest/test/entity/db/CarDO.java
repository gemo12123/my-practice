package org.mytest.test.entity.db;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author gemo
 * @date 2024/1/20 18:17
 */
@Data
public class CarDO {
    private String id;
    private String name;
    private String producer;
    private String country;
    private Double price;
    private LocalDateTime createTime;
}
