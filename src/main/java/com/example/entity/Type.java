package com.example.entity;

import java.util.List;
import java.util.Random;

public enum Type {

    SVN("svg"), PNG("png"), IMG("img");

    private static final List<Type> TYPES = List.of(values());
    private static final int SIZE = TYPES.size();
    private static final Random random = new Random();

    private final String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static final String getRandomType() {
        return TYPES.get(random.nextInt(SIZE)).getName();
    }

}
