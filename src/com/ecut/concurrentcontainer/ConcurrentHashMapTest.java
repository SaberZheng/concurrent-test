package com.ecut.concurrentcontainer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        Map map = Collections.synchronizedMap(new HashMap<>());

    }
}
