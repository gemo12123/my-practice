package org.mytest.test.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.mytest.test.entity.Dog;

import java.util.List;

/**
 * jackson基本使用
 *
 * @author gemo
 * @date 2024/7/31 21:20
 */
public class Jackson01Test {
    /**
     * String -> POJO
     */
    @Test
    public void test01(){
        ObjectMapper objectMapper = new ObjectMapper();
        String json="{\n" +
                "    \"name\":\"foo\",\n" +
                "    \"age\":\"5\"\n" +
                "}";
        try {
            Dog dog = objectMapper.readValue(json, Dog.class);
            System.out.println(dog);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * String -> List、Map
     */
    @Test
    public void test02(){
        ObjectMapper objectMapper = new ObjectMapper();
        String json="[\n" +
                "    {\n" +
                "        \"name\": \"foo\",\n" +
                "        \"age\": \"5\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"name\": \"bar\",\n" +
                "        \"age\": \"3\"\n" +
                "    }\n" +
                "]";
        try {
            List<Dog> list = objectMapper.readValue(json, List.class);
            System.out.println(list);
            // ClassCastException
//            for (Dog dog : list) {
//                System.out.println(dog.getClass().getName());
//            }
            List<Dog> list2 = objectMapper.readValue(json, new TypeReference<List<Dog>>() {});
            System.out.println(list2);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * POJO -> String
     */
    @Test
    public void test03(){
        Dog dog = new Dog();
        dog.setName("foo");
        dog.setAge(3);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(dog);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
