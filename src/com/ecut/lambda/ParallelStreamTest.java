package com.ecut.lambda;

import java.util.ArrayList;
import java.util.List;

public class ParallelStreamTest {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User(i, "user" + i));
        }
        double avg = users.parallelStream().mapToInt(User::getId).average().getAsDouble();
        System.out.println(avg);
    }
}
