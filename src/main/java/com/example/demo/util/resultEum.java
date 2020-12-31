package com.example.demo.util;

public enum resultEum {

    SUCCESS("操作成功")，
    ERROR("操作失败");


    private final String  type;


    private resultEum(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
