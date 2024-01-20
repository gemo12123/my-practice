package org.mytest.test;

import org.junit.Before;
import org.junit.Test;
import org.mytest.test.entity.db.CarDO;
import org.mytest.test.entity.dto.CarDTO;
import org.mytest.test.entity.dto.CarProducer;
import org.mytest.test.entity.vo.CarVO;
import org.mytest.test.mapper.CarConverter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author gemo
 * @date 2024/1/20 18:27
 */
public class CarTest {
    CarDO carDO;
    @Before
    public void beforeCar(){
        carDO = new CarDO();
        carDO.setId(UUID.randomUUID().toString());
        carDO.setName("帕萨特");
        carDO.setCountry("德国");
        carDO.setProducer("大众");
        carDO.setPrice(16.852);
        carDO.setCreateTime(LocalDateTime.now());
    }
    @Test
    public void test01(){
        CarVO carVO = CarConverter.INSTANCE.carDoToCarVo(carDO);
        System.out.println(carDO);
        System.out.println(carVO);
    }

    @Test
    public void test02(){
        CarDTO carDTO = new CarDTO();
        carDTO.setCarName("迈腾");
        carDTO.setPrice("26.78");
        carDTO.setCreateTime(LocalDateTime.now());
        CarProducer carProducer = new CarProducer();
        carProducer.setName("大众");
        carProducer.setLocation("德国");

        CarDO carDO1 = CarConverter.INSTANCE.mergeCarAndProducer(carDTO, carProducer);
        System.out.println(carDO1);

        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        System.out.println(CarConverter.INSTANCE.mergeCarAndProducerWithId(carDTO, carProducer,uuid));
    }
}
