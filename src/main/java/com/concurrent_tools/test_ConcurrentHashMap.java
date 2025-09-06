package com.concurrent_tools;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * ConcurrentHashMap
 */
public class test_ConcurrentHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String, LongAdder> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 2; i++) {
            LongAdder value = map.computeIfAbsent("key", (key) -> new LongAdder());
            value.increment();
            System.out.println(value);
        }




    }
}
