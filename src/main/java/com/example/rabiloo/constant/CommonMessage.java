package com.example.rabiloo.constant;

public enum CommonMessage {

    SUCCESS(0, "Thành công"),
    FAILED(-1, "Không thành công");
    public final int code;
    public String message;

    CommonMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    CommonMessage(int code) {
        this.code = code;
    }
}
