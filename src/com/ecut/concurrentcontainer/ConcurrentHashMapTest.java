package com.ecut.concurrentcontainer;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        Map map = Collections.synchronizedMap(new HashMap<>());

        new CopyOnWriteArrayList();

    }
}
