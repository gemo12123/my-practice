package org.mytest.test.constant;

import java.util.Arrays;

/**
 * @author gemo
 * @date 2024/1/17 20:57
 */
public enum PatrolType {
    /**
     * 未知
     */
    UNKNOWN("-1"),
    /**
     * 仅一次
     */
    ONLY_ONE("0"),
    /**
     * 每天
     */
    EVERY_DAY("1"),
    /**
     * 每周
     */
    EVERY_WEEK("2");

    private final String code;

    PatrolType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static PatrolType getByCode(String code) {
        return Arrays.stream(values())
                .filter(patrolType -> patrolType.getCode().equals(code))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
