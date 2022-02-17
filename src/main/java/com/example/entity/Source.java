package com.example.entity;

import java.util.List;
import java.util.Random;

public enum Source {
    MOBILE("mobile"), WEB("web");

    private static final List<Source> SOURCES = List.of(values());
    private static final Random random = new Random();
    private final String name;

    Source(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String getRandomSource() {
        return SOURCES.get(random.nextInt(values().length)).getName();
    }
}
