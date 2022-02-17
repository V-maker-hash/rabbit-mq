package com.example.rabbitmqconsumer.entity;

public enum Type {

    SVN("svn"), PNG("png"), IMG("img");

    private final String name;

    Type(String name) {
        this.name = name;
    }

}
