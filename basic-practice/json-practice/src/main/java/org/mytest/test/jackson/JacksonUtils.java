package org.mytest.test.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.Callable;

/**
 * jackson工具类
 *
 * @author gemo
 * @date 2024/8/1 11:39
 */
public class JacksonUtils {
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();;

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    public static <T> T tryParse(Callable<T> parser) {
        try {
            return parser.call();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
