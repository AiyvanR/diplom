package com.example.diploma.mappers;

import com.example.diploma.Entity.Order;
import com.example.diploma.dto.ImageDTO;
import com.example.diploma.dto.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrderDTOMapper implements Function<Order, OrderDTO> {



    @Override
    public OrderDTO apply(Order order){
        return new OrderDTO(
                order.getId(),
                order.getPublisher(),
                order.getTruckType(),
                order.getLoadLocation(),
                order.getDeliveryLocation(),
                order.getWeight(),
                order.getVolume(),
                order.getPrice(),
                order.getStatus(),
                order.getImages().stream().map(image -> new ImageDTO(image.getId(), image.getName(), image.getType(), "image-url")).toList()
        );
    }
}
