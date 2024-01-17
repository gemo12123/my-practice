package org.mytest.test.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

/**
 * @author gemo
 * @date 2024/1/17 21:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatrolTaskTimePeriodDTO {
    private String id;
    private String taskId;
    private String taskItemId;
    private LocalTime startTime;
    private LocalTime endTime;
    private String timePeriod;
}
