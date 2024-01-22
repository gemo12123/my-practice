package org.mytest.test.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author gemo
 * @date 2024/1/20 18:18
 */
@Data
public class CarVO {
    private String id;
    private String carName;
    private String producer;
    private String location;
    private String price;
    private String time;

    List<Object> list;
}
