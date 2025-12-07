package com.example.order.common;

import com.example.order.dto.OrderDTO;
import com.example.order.model.Orders;

public class SuccessOrderResponse {
    private final OrderDTO order;
    public SuccessOrderResponse(Orders order) {
        this.order = order;
    }
}
