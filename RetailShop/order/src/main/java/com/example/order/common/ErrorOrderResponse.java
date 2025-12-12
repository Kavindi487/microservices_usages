package com.example.order.common;
import lombok.Getter;

@Getter
public class ErrorOrderResponse extends OrderResponse {
    private final String erroMessage;

    public ErrorOrderResponse(String erroMessage) {
        this.erroMessage = erroMessage;
    }
}
