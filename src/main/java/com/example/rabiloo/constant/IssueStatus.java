package com.example.rabiloo.constant;

public enum IssueStatus {
    PENDING("Chờ duyệt"),
    REVIEWED("Đã được review");

    public String status;

    IssueStatus(String status) {
        this.status = status;
    }

}