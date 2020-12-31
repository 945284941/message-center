package com.example.demo.rabbitmq;

public enum BuiltinExchangeType {
    DIRECT("direct"),
    FANOUT("fanout"),
    TOPIC("topic"),
    HEADERS("headers");

    private final String type;

    private BuiltinExchangeType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
