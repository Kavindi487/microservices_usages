package com.example.order.service;

import com.example.order.dto.OrderDTO;
import com.example.order.model.Order;
import com.example.order.repo.OrderRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<OrderDTO> getAllUsers() {
        List<Order> orderList = orderRepo.findAll();
        return modelMapper.map(orderList, new TypeToken<List<OrderDTO>>(){}.getType());
    }

    public OrderDTO saveUsers(OrderDTO orderDTO) {
        orderRepo.save(modelMapper.map(orderDTO, Order.class));
        return orderDTO;
    }

    public OrderDTO updateUsers(OrderDTO orderDTO) {
        orderRepo.save(modelMapper.map(orderDTO, Order.class));
        return orderDTO;
    }

    public String deleteUsers(OrderDTO orderDTO) {
        orderRepo.delete(modelMapper.map(orderDTO, Order.class));
        return "user deleted";
    }
}
