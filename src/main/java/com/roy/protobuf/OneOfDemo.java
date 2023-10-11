package com.roy.protobuf;

import com.roy.model.Credentials;
import com.roy.model.EmailCredentials;
import com.roy.model.PhoneOTP;

public class OneOfDemo {

    public static void main(String[] args) {

        EmailCredentials emailCredentials = EmailCredentials.newBuilder()
                .setEmail("test@gmail.com")
                .setPassword("admin123")
                .build();

        PhoneOTP phoneOTP = PhoneOTP.newBuilder()
                .setNumber(123123123)
                .setCode(456)
                .build();

        Credentials credentials = Credentials.newBuilder()
                //.setEmailMode(emailCredentials)
                .setPhoneMode(phoneOTP) // can't set both, it has to be either or
                .build();

        login(credentials);
    }

    private static void login(Credentials credentials) {

        switch (credentials.getModeCase()) {
            case EMAILMODE:
                System.out.println(credentials.getEmailMode());
                break;
            case PHONEMODE:
                System.out.println(credentials.getPhoneMode());
                break;
        }
    }
}
