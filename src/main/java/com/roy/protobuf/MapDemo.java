package com.roy.protobuf;

import com.roy.model.BodyStyle;
import com.roy.model.Car;
import com.roy.model.Dealer;

public class MapDemo {

    public static void main(String[] args) {
        Car accord = Car.newBuilder()
                .setMake("Honda")
                .setModel("Accord")
                .setYear(2020)
                .setBodyStyle(BodyStyle.SEDAN)
                .build();

        Car civic = Car.newBuilder()
                .setMake("Honda")
                .setModel("Civic")
                .setYear(2005)
                .build();

        Dealer dealer = Dealer.newBuilder()
                .putModel(2020, accord)
                .putModel(2005, civic)
                .build();

        //System.out.println(dealer.getModelOrThrow(2004));

        System.out.println(dealer.getModelOrDefault(2004, accord));

        System.out.println(dealer.getModelOrThrow(2005).getBodyStyle());
    }
}
