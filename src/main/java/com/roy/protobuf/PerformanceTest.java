package com.roy.protobuf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.Int32Value;
import com.google.protobuf.InvalidProtocolBufferException;
import com.roy.json.JPerson;
import com.roy.model.Person;

import java.io.IOException;

public class PerformanceTest {

    public static void main(String[] args) {

        // json serialization deserialization
        JPerson person = new JPerson();
        person.setName("sam");
        person.setAge(10);
        ObjectMapper mapper = new ObjectMapper();


        Runnable json = () -> {
            try {
                byte[] bytes = mapper.writeValueAsBytes(person);
                JPerson person1 = mapper.readValue(bytes, JPerson.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        // protobuf serialization deserialization
        Person sam = Person.newBuilder()
                .setName("sam")
                //.setAge(10)
                .setAge(Int32Value.newBuilder().setValue(10).build())
                .build();


        Runnable proto = () -> {
            try {
                byte[] bytes = sam.toByteArray();
                Person sam1 = Person.parseFrom(bytes);
            } catch (InvalidProtocolBufferException e) {
                throw new RuntimeException(e);
            }
        };

        for (int i = 0; i < 5; i++) {
            runPerformanceTest(json, "json");
            runPerformanceTest(proto, "proto");
        }
    }

    private static void runPerformanceTest(Runnable runnable, String method) {
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 5_000_000; i++) {
            runnable.run();
        }
        long time2 = System.currentTimeMillis();

        System.out.println(method + " : " + (time2 - time1) + " ms");
    }
}
