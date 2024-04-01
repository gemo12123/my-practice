package org.mytest.test.impl;

import org.mytest.test.DataSource;

/**
 * @author gemo
 * @date 2024/3/30 18:40
 */
public class MysqlDataSource implements DataSource {
    @Override
    public void printName() {
        System.out.println("Mysql DataSource");
    }
}
