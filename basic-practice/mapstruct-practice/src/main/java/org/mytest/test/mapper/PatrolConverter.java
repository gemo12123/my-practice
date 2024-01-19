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
import java.util.List;

/**
 * @author gemo
 * @date 2024/1/17 21:12
 */
@Mapper
public interface PatrolConverter {
    PatrolConverter INSTANCE = Mappers.getMapper(PatrolConverter.class);

    @Named("taskdo2dto")
    PatrolTaskDTO taskdo2dto(PatrolTaskDO taskDO);
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    @Mapping(target = "name", constant = "constant")
    @Named("taskdo2dtoWithId")
    PatrolTaskDTO taskdo2dtoWithId(PatrolTaskDO taskDO);

    @IterableMapping(qualifiedByName = "taskdo2dtoWithId")
    List<PatrolTaskDTO> taskdo2dto(List<PatrolTaskDO> tasks);

    PatrolTaskDO dto2taskdo(PatrolTaskDTO taskDTO);

    @Mapping(target = "type", ignore = true)
    PatrolTaskItemDTO taskItem2dto(PatrolTaskItemDO taskItemDO);

    @Mapping(target = "type", ignore = true)
    PatrolTaskItemDO dto2taskItem(PatrolTaskItemDTO taskItem);

    /**
     * 这里如果使用了建造者模式，则@MappingTarget务必使用其Builder对象，参看https://github.com/mapstruct/mapstruct/issues/3176
     *
     * @param taskItemDO
     * @param taskItemDTO
     */
    @AfterMapping
    default void taskItemDTOTypeCast(PatrolTaskItemDO taskItemDO, @MappingTarget PatrolTaskItemDTO.PatrolTaskItemDTOBuilder taskItemDTO) {
        String type = taskItemDO.getType();
        if (type != null) {
            taskItemDTO.type(PatrolType.getByCode(type));
        }
    }

    @AfterMapping
    default void taskItemDOTypeCast(@MappingTarget PatrolTaskItemDO.PatrolTaskItemDOBuilder taskItemDO, PatrolTaskItemDTO taskItemDTO) {
        PatrolType type = taskItemDTO.getType();
        if (type != null) {
            taskItemDO.type(type.getCode());
        }
    }

    @Mapping(source = "start", target = "startTime")
    @Mapping(source = "end", target = "endTime")
    @Mapping(target = "timePeriod", expression = "java(taskTimePeriodDO.getStart()+'_'+taskTimePeriodDO.getEnd())")
    PatrolTaskTimePeriodDTO taskTimePeriod2dto(PatrolTaskTimePeriodDO taskTimePeriodDO);

    @Mappings(
            value = {
                    // 指定格式数字使用numberFormat，日期使用dateFormat
                    @Mapping(source = "startTime", target = "start",dateFormat = "HH:mm:ss"),
                    @Mapping(source = "endTime", target = "end",dateFormat = "HH:mm:ss SSS")
            }
    )
    PatrolTaskTimePeriodDO dto2taskTimePeriod(PatrolTaskTimePeriodDTO taskTimePeriod);

    /**
     * @InheritInverseConfiguration 反向继承
     * 只会继承@Mapping，不会继承@BeanMapping
     *
     * @param taskTimePeriod
     * @return
     */
    @InheritInverseConfiguration(name = "taskTimePeriod2dto")
    PatrolTaskTimePeriodDO dto2taskTimePeriod2(PatrolTaskTimePeriodDTO taskTimePeriod);


    /**
     * 这里如果使用了建造者模式，则@MappingTarget务必使用其Builder对象
     *
     * @param taskTimePeriodDTO
     */
    @AfterMapping
    default void taskTimePeriodSet(@MappingTarget PatrolTaskTimePeriodDTO.PatrolTaskTimePeriodDTOBuilder taskTimePeriodDTO) {
        PatrolTaskTimePeriodDTO build = taskTimePeriodDTO.build();
        LocalTime startTime = build.getStartTime();
        LocalTime endTime = build.getEndTime();
        taskTimePeriodDTO.timePeriod(String.valueOf(startTime) + "—" + String.valueOf(endTime));
    }

}
