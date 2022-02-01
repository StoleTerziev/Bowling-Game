package com.bowling.game.model;

public class MessageResponse<T> {
	
	private T data;

    public MessageResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
