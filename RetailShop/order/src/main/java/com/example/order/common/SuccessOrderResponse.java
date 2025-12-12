package com.example.order.common;

import com.example.order.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;


@Getter
public class SuccessOrderResponse extends OrderResponse {
    @JsonUnwrapped
    private final OrderDTO order;
    public SuccessOrderResponse(OrderDTO order) {
        this.order = order;
    }
}
