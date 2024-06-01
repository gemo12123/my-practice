package org.mytest.test;

import cn.hutool.core.util.StrUtil;

public class StringTest {
    public static void main(String[] args) {
        String s = "  ";
        System.out.println("isEmpty result:" + StringUtils.isEmpty(s));
        System.out.println("isBlank result:" + StringUtils.isBlank(s));

        System.out.println("hutool isEmpty result:" + StrUtil.isEmpty(s));
        System.out.println("hutool isBlank result:" + StrUtil.isBlank(s));

    }
}
