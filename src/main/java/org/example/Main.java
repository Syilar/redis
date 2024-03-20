package org.example;

public class Main {

    public static void main(String[] args) {

        RedisStorage redisStorage = new RedisStorage();
        redisStorage.init();
        redisStorage.registrationUsers();

        redisStorage.getListUsers();
        int i = 0;
//        while (true) {
//            redisStorage.g
//        }
    }
}