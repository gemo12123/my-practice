package org.mytest.test.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author gemo
 * @date 2024/1/20 18:35
 */
@Data
public class CarDTO {
    private String carName;
    private String price;
    private LocalDateTime createTime;
}
