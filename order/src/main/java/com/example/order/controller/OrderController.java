package com.example.order.controller;

import com.example.order.dto.OrderDTO;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")

public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/getUser")
    public List<OrderDTO> getUsers(){

        return orderService.getAllUsers();
    }

    @PostMapping("/addUser")
    public OrderDTO saveUser(@RequestBody OrderDTO orderDTO){
        return orderService.saveUsers(orderDTO);
    }

    @PostMapping("/updateUser")
    public OrderDTO updateUser(@RequestBody OrderDTO orderDTO){
        return orderService.updateUsers(orderDTO);
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestBody OrderDTO orderDTO){
        return orderService.deleteUsers(orderDTO);
    }
}

