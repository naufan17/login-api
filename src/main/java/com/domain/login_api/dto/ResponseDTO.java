package com.domain.login_api.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseDTO<T> {

    private boolean status;

    private List<String> messages = new ArrayList<>();

    private T data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
