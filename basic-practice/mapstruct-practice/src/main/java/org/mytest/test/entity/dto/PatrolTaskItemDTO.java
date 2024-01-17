package org.mytest.test.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mytest.test.constant.PatrolType;

import java.util.List;

/**
 * @author gemo
 * @date 2024/1/17 20:56
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatrolTaskItemDTO {
    private String id;
    private String taskId;
    private String name;
    private PatrolType type;
    List<PatrolTaskTimePeriodDTO> taskTimePeriodList;
}
