package com.roy.protobuf;

import com.google.protobuf.Int32Value;
import com.roy.model.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PersonDemo {

    public static void main(String[] args) throws IOException {

        Person sam = Person.newBuilder()
                .setName("sam")
                //.setAge(10)
                .setAge(Int32Value.newBuilder().setValue(25).build())
                .build();

        /*Person sam2 = Person.newBuilder()
                .setName("Sam")
                .setAge(10)
                .build();
        System.out.println(sam.equals(sam2));*/

        Path path = Paths.get("sam.ser");
        Files.write(path, sam.toByteArray());

        byte[] bytes = Files.readAllBytes(path);
        Person newSam = Person.parseFrom(bytes);
        System.out.println(newSam);
    }
}
