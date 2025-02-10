package com.example.diploma.Service;

import com.example.diploma.Entity.Image;
import com.example.diploma.Entity.Order;
import com.example.diploma.Entity.enums.OrderStatus;
import com.example.diploma.dto.OrderDTO;
import com.example.diploma.dto.OrderRequest;

import java.util.List;
import java.util.UUID;


public interface OrderService {
    public OrderDTO create(OrderRequest request);
    public OrderDTO getOrderById(UUID id);
    public List<OrderDTO> getAllOrders();
    public void DeleteOrder(UUID id);
    public OrderDTO updateOrder(UUID id,OrderRequest request);
    public OrderDTO updateOrderStatus(UUID id, OrderStatus status);


}
