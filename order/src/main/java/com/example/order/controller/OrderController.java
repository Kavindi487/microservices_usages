package com.example.order.controller;

import com.example.order.dto.OrderDTO;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // GET ALL ORDERS
    @GetMapping("/get")
    public List<OrderDTO> getOrders() {
        return orderService.getAllOrders();
    }

    // ADD ORDER
    @PostMapping("/add")
    public OrderDTO saveOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.saveOrder(orderDTO);
    }

    // UPDATE ORDER
    @PutMapping("/update")
    public OrderDTO updateOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.updateOrder(orderDTO);
    }

    // DELETE ORDER
    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Integer id) {
        return orderService.deleteOrder(id);
    }

    // GET ORDER BY ID
    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }
}
