package com.cal.calbackend.common.model;

public class SuccessResponse<T> {
    private String message;
    private T data = null;

    public SuccessResponse(String message) {
        this.message = message;
    }

    public SuccessResponse(String message, T data) {
        this.message = message;
        this.setData(data);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
