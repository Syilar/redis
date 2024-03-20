package org.example;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;


import java.util.Date;

import static java.lang.System.out;

public class RedisStorage {

    private RedissonClient redisson;

    private RKeys rKeys;

    private RScoredSortedSet<String> users;

    private final static String KEY = "USERS";

    private static final int COUNT_USERS = 20;

    public void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
        rKeys = redisson.getKeys();
        users = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }

    public void shutdown() {
        redisson.shutdown();
    }

    public void log(int userId) {
        out.println("- На главной странице показываем пользователя " + userId);
    }

    public void registrationUsers() {
        for (int i = 0; i < COUNT_USERS; i++) {
            users.add(getTs(), String.valueOf(i));
        }
    }

    public void getListUsers() {
        out.println(users.entryRange(0, -1));
    }

    private double getTs() {
        return new Date().getTime() / 1000;
    }
}
