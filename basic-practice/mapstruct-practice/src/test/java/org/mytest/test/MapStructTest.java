package org.mytest.test;

import org.junit.Before;
import org.junit.Test;
import org.mytest.test.constant.PatrolType;
import org.mytest.test.entity.db.PatrolTaskDO;
import org.mytest.test.entity.db.PatrolTaskItemDO;
import org.mytest.test.entity.db.PatrolTaskTimePeriodDO;
import org.mytest.test.entity.dto.PatrolTaskDTO;
import org.mytest.test.entity.dto.PatrolTaskItemDTO;
import org.mytest.test.entity.dto.PatrolTaskTimePeriodDTO;
import org.mytest.test.mapper.PatrolConverter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author gemo
 * @date 2024/1/17 21:19
 */
public class MapStructTest {
    PatrolTaskDO patrolTaskDO;
    PatrolTaskDTO patrolTaskDTO;
    PatrolTaskItemDO patrolTaskItemDO;
    PatrolTaskItemDTO patrolTaskItemDTO;
    PatrolTaskTimePeriodDO patrolTaskTimePeriodDO;
    PatrolTaskTimePeriodDTO patrolTaskTimePeriodDTO;

    @Before
    public void buildTask() {
        patrolTaskDO = PatrolTaskDO.builder()
                .id("t1")
                .name("task-01")
                .build();
        patrolTaskDTO = PatrolTaskDTO.builder()
                .id("t1")
                .name("task-01")
                .build();
    }

    @Before
    public void buildTaskItem() {
        patrolTaskItemDO = PatrolTaskItemDO.builder()
                .id("ti1")
                .taskId("t1")
                .name("item-01")
                .type("1")
                .build();
        patrolTaskItemDTO = PatrolTaskItemDTO.builder()
                .id("ti1")
                .taskId("t1")
                .name("item-01")
                .type(PatrolType.EVERY_DAY)
                .build();
    }

    @Before
    public void buildTaskTimePeriod() {
        patrolTaskTimePeriodDO = PatrolTaskTimePeriodDO.builder()
                .id("ttp1")
                .taskId("t1")
                .taskItemId("ti1")
                //格式必须为HH:mm
                .start("08:00")
                .end("12:00")
                .build();
        patrolTaskTimePeriodDTO = PatrolTaskTimePeriodDTO.builder()
                .id("tp1")
                .taskId("t1")
                .taskItemId("ti1")
                .startTime(LocalTime.of(8,0))
                .endTime(LocalTime.of(12,0))
                .build();
    }

    @Test
    public void testTask01() {
        System.out.println(patrolTaskDO);
        System.out.println(PatrolConverter.INSTANCE.taskdo2dto(patrolTaskDO));
    }
    @Test
    public void testTask02(){
        PatrolTaskDTO cast = PatrolConverter.INSTANCE.taskdo2dto(patrolTaskDO);
        PatrolTaskDO pt2 = PatrolConverter.INSTANCE.dto2taskdo(cast);
        pt2.setId("22222");
        List<PatrolTaskDO> tasks = Arrays.asList(patrolTaskDO, pt2);
        System.out.println(PatrolConverter.INSTANCE.taskdo2dto(tasks));
    }
    @Test
    public void testTask03() {
        System.out.println(patrolTaskDO);
        System.out.println(PatrolConverter.INSTANCE.taskdo2dtoWithId(patrolTaskDO));
    }

    @Test
    public void testTaskItem01(){
        System.out.println(patrolTaskItemDO);
        System.out.println(PatrolConverter.INSTANCE.taskItem2dto(patrolTaskItemDO));
    }
    @Test
    public void testTaskItem02(){
        System.out.println(patrolTaskItemDTO);
        System.out.println(PatrolConverter.INSTANCE.dto2taskItem(patrolTaskItemDTO));
    }

    @Test
    public void testTimePeriod01(){
        System.out.println(patrolTaskTimePeriodDO);
        System.out.println(PatrolConverter.INSTANCE.taskTimePeriod2dto(patrolTaskTimePeriodDO));
    }
    @Test
    public void testTimePeriod02(){
        System.out.println(patrolTaskTimePeriodDTO);
        System.out.println(PatrolConverter.INSTANCE.dto2taskTimePeriod(patrolTaskTimePeriodDTO));
        System.out.println(PatrolConverter.INSTANCE.dto2taskTimePeriod2(patrolTaskTimePeriodDTO));
    }
}
