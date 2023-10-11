package com.roy.protobuf;

import com.google.protobuf.Int32Value;
import com.roy.model.Address;
import com.roy.model.Car;
import com.roy.model.Person;

import java.util.ArrayList;

public class CompositionDemo {

    public static void main(String[] args) {
        Address address = Address.newBuilder()
                .setPostbox(123)
                .setStreet("main street")
                .setCity("Atlanta")
                .build();

        Car accord = Car.newBuilder()
                .setMake("Honda")
                .setModel("Accord")
                .setYear(2020)
                .build();

        Car civic = Car.newBuilder()
                .setMake("Honda")
                .setModel("Civic")
                .setYear(2005)
                .build();
        var cars = new ArrayList<Car>();
        cars.add(accord);
        cars.add(civic);
        Person sam = Person.newBuilder()
                .setName("sam")
                //.setAge(25)
                .setAge(Int32Value.newBuilder().setValue(25).build())
                .setAddress(address)
                /*.addCar(accord)
                .addCar(civic)*/
                .addAllCar(cars)
                .build();

        System.out.println(sam.hasAge());

    }
}
