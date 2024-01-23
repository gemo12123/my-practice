package org.mytest.test.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.mytest.test.entity.db.CarDO;
import org.mytest.test.entity.dto.CarDTO;
import org.mytest.test.entity.dto.CarProducer;
import org.mytest.test.entity.vo.CarVO;

import java.util.Map;

/**
 * @author gemo
 * @date 2024/1/20 18:21
 */
@Mapper
public interface CarConverter {

    CarConverter INSTANCE = Mappers.getMapper(CarConverter.class);

    @Mapping(source = "name", target = "carName")
    @Mapping(source = "country", target = "location")
    @Mapping(target = "price", numberFormat = "￥#.00万元")
    @Mapping(source = "createTime", target = "time", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "list", target = "list")
    CarVO carDoToCarVo(CarDO car);

    @Mapping(target = "id", expression = "java(carProducer.getName() + carDTO.getCarName() + '-' + java.util.UUID.randomUUID())")
    @Mapping(target = "name", source = "carDTO.carName")
    @Mapping(target = "country", source = "carProducer.location")
    @Mapping(target = "producer", source = "carProducer.name")
    CarDO mergeCarAndProducer(CarDTO carDTO, CarProducer carProducer);

    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "name", source = "carDTO.carName")
    @Mapping(target = "country", source = "carProducer.location")
    @Mapping(target = "producer", source = "carProducer.name")
    CarDO mergeCarAndProducerWithId(CarDTO carDTO, CarProducer carProducer, String uuid);

    CarVO update(CarDO car, @MappingTarget CarVO carVO);
    void update2(CarDO car, @MappingTarget CarVO carVO);

    @Mapping(source = "name", target = "carName")
    @Mapping(source = "country", target = "location")
    @Mapping(target = "list", ignore = true)
    CarVO mapToCarVo(Map<String, String> carMap);

    @Named("test")
    @Condition
    default boolean isCheck(String s){
        if (s==null) {
            return false;
        }
        return s.equals("zs");
    }

}
