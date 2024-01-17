package org.mytest.test.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.mytest.test.constant.PatrolType;
import org.mytest.test.entity.db.PatrolTaskDO;
import org.mytest.test.entity.db.PatrolTaskItemDO;
import org.mytest.test.entity.db.PatrolTaskTimePeriodDO;
import org.mytest.test.entity.dto.PatrolTaskDTO;
import org.mytest.test.entity.dto.PatrolTaskItemDTO;
import org.mytest.test.entity.dto.PatrolTaskTimePeriodDTO;

import java.time.LocalTime;

/**
 * @author gemo
 * @date 2024/1/17 21:12
 */
@Mapper
public interface PatrolConverter {
    PatrolConverter INSTANCE = Mappers.getMapper(PatrolConverter.class);

    PatrolTaskDTO taskdo2dto(PatrolTaskDO taskDO);

    @Mapping(target = "type", ignore = true)
    PatrolTaskItemDTO taskItem2dto(PatrolTaskItemDO taskItemDO);

    /**
     * 这里如果使用了建造者模式，则@MappingTarget务必使用其Builder对象
     *
     * @param taskItemDO
     * @param taskItemDTO
     */
    @AfterMapping
    default void taskItemTypeCast(PatrolTaskItemDO taskItemDO,@MappingTarget PatrolTaskItemDTO.PatrolTaskItemDTOBuilder taskItemDTO) {
        System.out.println("after mapping");
        taskItemDTO.type(PatrolType.getByCode(taskItemDO.getType()));
    }

    @Mapping(source = "start", target = "startTime")
    @Mapping(source = "end", target = "endTime")
    PatrolTaskTimePeriodDTO taskTimePeriod2dto(PatrolTaskTimePeriodDO taskTimePeriodDO);

    /**
     * 这里如果使用了建造者模式，则@MappingTarget务必使用其Builder对象
     *
     * @param taskTimePeriodDTO
     */
    @AfterMapping
    default void taskTimePeriodSet(@MappingTarget PatrolTaskTimePeriodDTO.PatrolTaskTimePeriodDTOBuilder taskTimePeriodDTO){
        PatrolTaskTimePeriodDTO build = taskTimePeriodDTO.build();
        LocalTime startTime = build.getStartTime();
        LocalTime endTime = build.getEndTime();
        taskTimePeriodDTO.timePeriod(String.valueOf(startTime)+"—"+String.valueOf(endTime));
    }

}
