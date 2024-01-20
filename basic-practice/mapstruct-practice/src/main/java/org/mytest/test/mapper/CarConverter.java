package org.mytest.test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.mytest.test.entity.db.CarDO;
import org.mytest.test.entity.dto.CarDTO;
import org.mytest.test.entity.dto.CarProducer;
import org.mytest.test.entity.vo.CarVO;

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

}