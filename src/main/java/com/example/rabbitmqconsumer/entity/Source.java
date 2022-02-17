package com.example.rabbitmqconsumer.entity;

public enum Source {
    MOBILE("mobile"), WEB("web");

    private final String name;

    Source(String name) {
        this.name = name;
    }
}
