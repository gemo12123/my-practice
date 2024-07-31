package org.mytest.test.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;
import org.mytest.test.entity.Dog;

import java.util.Iterator;
import java.util.Map;

/**
 * jackson json树模型：JsonNode
 *
 * @author gemo
 * @date 2024/7/31 21:40
 */
public class Jackson02Test {

    /**
     * String -> JsonNode
     */
    @Test
    public void test01() {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\n" +
                "    \"name\":\"foo\",\n" +
                "    \"age\":\"5\"\n" +
                "}";
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            System.out.println(jsonNode.isArray());
            System.out.println(jsonNode.isValueNode());
            System.out.println(jsonNode.isObject());
            System.out.println(jsonNode.get("name").asText());
            System.out.println(jsonNode.get("age").asInt());
            System.out.println(jsonNode.get("sex") == null);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * POJO -> JsonNode
     */
    @Test
    public void test02() {
        Dog dog = new Dog();
        dog.setName("foo");
        dog.setAge(3);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.valueToTree(dog);
        System.out.println(jsonNode.isArray());
        System.out.println(jsonNode.isValueNode());
        System.out.println(jsonNode.isObject());
        System.out.println(jsonNode.get("name").asText());
        System.out.println(jsonNode.get("age").asInt());
    }

    /**
     * JsonNode -> String
     */
    @Test
    public void test03() {
        Dog dog = new Dog();
        dog.setName("foo");
        dog.setAge(3);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.valueToTree(dog);
        try {
            String json = objectMapper.writeValueAsString(jsonNode);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 路径访问
     */
    @Test
    public void test04() {
        String json = "{\n" +
                "    \"animal\": {\n" +
                "        \"dog\": {\n" +
                "            \"name\": \"foo\",\n" +
                "            \"age\": \"5\"\n" +
                "        },\n" +
                "        \"cat\": {\n" +
                "            \"name\": \"bar\",\n" +
                "            \"age\": \"3\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            JsonNode dogName = jsonNode.at("/animal/dog/name");
            System.out.println(dogName.asText());
            JsonNode catSex = jsonNode.at("/animal/cat/sex");
            System.out.println(catSex.isMissingNode());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 路径访问
     */
    @Test
    public void test05() {
        String json = "[\n" +
                "    {\n" +
                "        \"name\": \"foo\",\n" +
                "        \"age\": \"5\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"name\": \"bar\",\n" +
                "        \"age\": \"3\"\n" +
                "    }\n" +
                "]";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            JsonNode name = jsonNode.at("/name");
            System.out.println(name);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * ObjectNode
     */
    @Test
    public void test06(){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("name", "person");
        Dog dog = new Dog();
        dog.setName("foo");
        dog.setAge(3);
        objectNode.set("dog", objectMapper.valueToTree(dog));

        try {
            String json = objectMapper.writeValueAsString(objectNode);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test07(){
        String json="{\n" +
                "    \"animal\": {\n" +
                "        \"dog\": {\n" +
                "            \"name\": \"foo\",\n" +
                "            \"age\": \"5\"\n" +
                "        },\n" +
                "        \"cat\": {\n" +
                "            \"name\": \"bar\",\n" +
                "            \"age\": \"3\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
            while (fields.hasNext()){
                Map.Entry<String, JsonNode> next = fields.next();
                System.out.println(next.getKey()+"===="+next.getValue());
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
