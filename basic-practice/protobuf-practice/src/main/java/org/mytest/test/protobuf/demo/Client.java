package org.mytest.test.protobuf.demo;

import com.google.protobuf.InvalidProtocolBufferException;
import org.mytest.test.protobuf.StudentProtoBuf;

public class Client {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        StudentProtoBuf.Student student = StudentProtoBuf.Student.newBuilder()
                .setName("zs")
                .setAge(10)
                .setAddress("China")
                .build();
        byte[] bytes = student.toByteArray();
        for (byte aByte : bytes) {
            System.out.print(aByte);
        }
        System.out.println();

        StudentProtoBuf.Student parseResult = StudentProtoBuf.Student.parseFrom(bytes);
        System.out.println(parseResult);
    }
}
