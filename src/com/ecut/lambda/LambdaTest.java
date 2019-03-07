package com.ecut.lambda;

import java.util.ArrayList;
import java.util.List;

public class LambdaTest {

    @FunctionalInterface
    interface UserFactory<U extends User> {
        U creat(int id, String name);
    }

    static UserFactory<User> userFactory = User::new;

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(userFactory.creat(i, "user" + Integer.toString(i)));
        }
        users.stream().map(User::getName).forEach(System.out::println);
    }
}
