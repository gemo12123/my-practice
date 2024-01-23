package org.mytest.test;

import org.junit.Test;
import org.mytest.test.mapper.FishConverter;

public class Test01 {
    @Test
    public void test01() {
        FishConverter.FishTank fishTank = new FishConverter.FishTank();
        fishTank.setMaterial("size");
        fishTank.setHeight(10);
        fishTank.setWidth(5);
        fishTank.setLength(10);
        FishConverter.FishTankWithVolumeDto map = FishConverter.INSTANCE.map(fishTank);
        System.out.println(map);
    }
}
