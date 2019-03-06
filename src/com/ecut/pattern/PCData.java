package com.ecut.pattern;

public class PCData {

    private final int intData;

    public PCData(String s) {
        intData = Integer.valueOf(s);
    }

    public PCData(int i) {
        intData = i;
    }

    @Override
    public String toString() {

        return "PCdata:" + intData;
    }
}
