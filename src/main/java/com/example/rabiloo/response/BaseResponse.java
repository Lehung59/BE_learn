package com.example.rabiloo.response;


import com.example.rabiloo.constant.CommonMessage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    @JsonIgnore
    private CommonMessage common;

    private long code;

    private String message;

    private T data;

    public BaseResponse(CommonMessage common) {
        this.code = common.code;
        this.message = common.message;
    }

    public BaseResponse(CommonMessage common, T data) {
        this.code = common.code;
        this.message = common.message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
