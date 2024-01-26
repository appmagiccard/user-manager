package com.magicauction.usermanager.utils;

public abstract class HashUtils {
    public static String encrypt(String name, String password) {
        //TODO: FINISH THIS!!
        return name+":"+password;
    }

    public static String decrypt(String password) {
        //TODO: FINISH THIS!!
        if(!password.contains(":"))
            throw new IllegalArgumentException("Password is not valid");

        String[] split = password.split(":");
        return split[1];
    }
}
