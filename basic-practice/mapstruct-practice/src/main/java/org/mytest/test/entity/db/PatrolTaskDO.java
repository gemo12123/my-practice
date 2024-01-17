package org.mytest.test.entity.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gemo
 * @date 2024/1/17 20:55
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatrolTaskDO {
    private String id;
    private String name;
}
