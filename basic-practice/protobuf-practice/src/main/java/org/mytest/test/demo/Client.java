package org.mytest.test.demo;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
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

        System.out.println(JsonFormat.printer().print(parseResult));
    }
}
