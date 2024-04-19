package org.mytest.test;

import org.junit.Test;

import java.util.ServiceLoader;

/**
 * @author gemo
 * @date 2024/3/30 18:42
 */
public class SpiTest {

    @Test
    public void test01() {
        ServiceLoader<DataSource> dataSources = ServiceLoader.load(DataSource.class);
        dataSources.forEach(DataSource::printName);
    }
}
