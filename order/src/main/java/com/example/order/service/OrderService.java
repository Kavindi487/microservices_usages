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

    // GET ALL ORDERS
    public List<OrderDTO> getAllOrders() {
        List<Order> orderList = orderRepo.findAll();
        return modelMapper.map(orderList, new TypeToken<List<OrderDTO>>() {}.getType());
    }

    // SAVE ORDER
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        orderRepo.save(modelMapper.map(orderDTO, Order.class));
        return orderDTO;
    }

    // UPDATE ORDER
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        orderRepo.save(modelMapper.map(orderDTO, Order.class));
        return orderDTO;
    }

    // DELETE ORDER BY ID
    public String deleteOrder(Integer id) {
        orderRepo.deleteById(id);
        return "Order deleted";
    }

    // GET ORDER BY ID
    public OrderDTO getOrderById(Integer id) {
        Order order = orderRepo.findById(id).orElse(null);
        if (order == null) {
            return null; // Or throw new EntityNotFoundException("Order not found");
        }
        return modelMapper.map(order, OrderDTO.class);
    }
}
