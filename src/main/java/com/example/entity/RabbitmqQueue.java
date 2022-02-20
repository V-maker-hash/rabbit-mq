package com.example.entity;

public class RabbitmqQueue {

    private long messages;

    private String name;

    public long getMessages() {
        return messages;
    }

    public void setMessages(long messages) {
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RabbitmqQueue(long messages, String name) {
        this.messages = messages;
        this.name = name;
    }

    public RabbitmqQueue() {
    }

    @Override
    public String toString() {
        return "RabbitmqQueue{" +
                "messages=" + messages +
                ", name='" + name + '\'' +
                '}';
    }

    public boolean isDirty() {
        return this.messages > 3;
    }
}
