package org.mytest.test.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author gemo
 * @date 2024/1/17 21:01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatrolTaskDTO {
    private String id;
    private String name;
    List<PatrolTaskItemDTO> taskItemList;
}
