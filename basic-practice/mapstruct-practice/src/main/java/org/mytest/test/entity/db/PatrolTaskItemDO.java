package org.mytest.test.entity.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gemo
 * @date 2024/1/17 20:56
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatrolTaskItemDO {
    private String id;
    private String taskId;
    private String name;
    private String type;
}
