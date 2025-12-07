package com.example.order.service;

import com.example.inventry.dto.InventoryDTO;
import com.example.order.dto.OrderDTO;
import com.example.order.model.Orders;
import com.example.order.repo.OrderRepo;
import jakarta.persistence.criteria.Order;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Transactional
public class OrderService {
    private final WebClient webClient;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public OrderService(WebClient webClient) {
        this.webClient = webClient;
    }

    // GET ALL ORDERS
    public List<OrderDTO> getAllOrders() {
        List<Orders> ordersList = orderRepo.findAll();
        return modelMapper.map(ordersList, new TypeToken<List<OrderDTO>>() {}.getType());
    }

    // SAVE ORDER
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        Integer itemId = orderDTO.getItemId();

        try{
           InventoryDTO inventoryResponse = webClient.get()
                    .uri(uriBuilder ->uriBuilder.path("/api/v1/orders/{id}").build(itemId))
                    .retrieve()
                    .bodyToMono(InventoryDTO.class)
                    .block();

        }catch (Exception e){
            e.printStackTrace();
        }

        orderRepo.save(modelMapper.map(orderDTO, Orders.class));
        return orderDTO;
    }

    // UPDATE ORDER
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        orderRepo.save(modelMapper.map(orderDTO, Orders.class));
        return orderDTO;
    }

    // DELETE ORDER BY ID
    public String deleteOrder(Integer id) {
        orderRepo.deleteById(id);
        return "Order deleted";
    }

    // GET ORDER BY ID
    public OrderDTO getOrderById(Integer orderId) {
        Orders order = orderRepo.findById(orderId).orElse(null);
        return modelMapper.map(order, OrderDTO.class);
    }
}
