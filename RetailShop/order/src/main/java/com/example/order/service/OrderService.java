package com.example.order.service;

import com.example.inventry.dto.InventoryDTO;
import com.example.order.common.ErrorOrderResponse;
import com.example.order.common.SuccessOrderResponse;
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
import org.springframework.web.reactive.function.client.WebClientResponseException;

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

            assert inventoryResponse != null;

            Integer productId = inventoryResponse.getProductId();

            ProductDTO productResponse = productWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/product/{productId}").build(productId))
                    .retrieve()
                    .bodyToMono(ProductDTO.class)
                    .block();

            assert productResponse != null;

           if(inventoryResponse.getQuantity() > 0){
               if (productResponse.getForSale() == 1) {
                   orderRepo.save(modelMapper.map(OrderDTO, Orders.class));
               }
               else {
                   return new ErrorOrderResponse("This item is not for sale");
               }
               return new SuccessOrderResponse(OrderDTO);
           }else{
               return new ErrorOrderResponse("Item not available, please try later");
           }

        }catch (WebClientResponseException e){
            if (e.getStatusCode().is5xxServerError()) {
                return new ErrorOrderResponse("Item not found");
            }
        }

        return null;
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
