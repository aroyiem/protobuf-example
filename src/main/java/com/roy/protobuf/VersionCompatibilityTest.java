package com.roy.protobuf;

import com.roy.model.Television;
import com.roy.model.Type;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VersionCompatibilityTest {

    public static void main(String[] args) throws IOException {

        Path pathV1 = Paths.get("tv-v1");
        Path pathV2 = Paths.get("tv-v2");

        /*Television tv = Television.newBuilder()
                .setBrand("sony")
                .setYear(2015)
                .build();

        // serialize
        Files.write(Paths.get("tv-v1"), tv.toByteArray());*/

        Television tv = Television.newBuilder()
                .setBrand("sony")
                .setModel(2016)
                .setType(Type.OLED)
                .build();
        // serialize
        Files.write(pathV2, tv.toByteArray());

        // de-serialize
        byte[] bytes = Files.readAllBytes(pathV1);
        System.out.println("V1 " + Television.parseFrom(bytes));

        bytes = Files.readAllBytes(pathV2);
        System.out.println("V2 " + Television.parseFrom(bytes));
    }
}
