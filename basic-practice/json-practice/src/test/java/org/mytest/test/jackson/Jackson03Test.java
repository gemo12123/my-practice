package org.mytest.test.jackson;

import org.junit.Test;
import org.mytest.test.entity.Dog;

/**
 * jackson工具类
 *
 * @author gemo
 * @date 2024/8/1 11:40
 */
public class Jackson03Test {
    @Test
    public void test01(){
        String json="{\n" +
                "    \"name\":\"foo\",\n" +
                "    \"age\":\"5\"\n" +
                "}";
        Dog dog = JacksonUtils.tryParse(() -> JacksonUtils.getObjectMapper()
                .readValue(json, Dog.class));
        System.out.println(dog);

        String jsonStr = JacksonUtils.tryParse(() -> JacksonUtils.getObjectMapper()
                .writeValueAsString(dog));
        System.out.println(jsonStr);
    }
}
