package com.example.diploma.Service.impl;

import com.example.diploma.Entity.Image;
import com.example.diploma.Entity.Order;
import com.example.diploma.Entity.enums.OrderStatus;
import com.example.diploma.Entity.user.User;
import com.example.diploma.Repository.ImageRepository;
import com.example.diploma.Repository.OrderRepository;
import com.example.diploma.Repository.UserRepository;
import com.example.diploma.Service.ImageService;
import com.example.diploma.Service.OrderService;
import com.example.diploma.dto.OrderDTO;
import com.example.diploma.dto.OrderRequest;
import com.example.diploma.exceptions.OrderNotFoundException;
import com.example.diploma.mappers.OrderDTOMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDTOMapper orderDTOMapper;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final ImageService imageService;


    @Override
    public OrderDTO create(OrderRequest request) {
        Order order = new Order();

        User publisher = userRepository.findById(request.getPublisherId()).orElseThrow(()->new EntityNotFoundException("User not found"));

        order.setPublisher(publisher);
        order.setTruckType(request.getTruckType());
        order.setLoadLocation(request.getLoadLocation());
        order.setDeliveryLocation(request.getDeliveryLocation());
        order.setWeight(request.getWeight());
        order.setVolume(request.getVolume());
        order.setPrice(request.getPrice());
        order.setImages(getImages(request.getListOfImagesId()));

        return orderDTOMapper.apply(orderRepository.save(order));
    }

    private List<Image> getImages(List<UUID> listOfImageIds){
        log.info("Fetching all images from one order");
        return listOfImageIds.stream().map(id -> imageRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Image not found with id: " + id))).toList();

//        return listOfImageIds.stream().map(imageService::get).toList();
    }


    @Override
    public OrderDTO getOrderById(UUID id) {
        log.info("Fetching order with id {}", id);
        return orderRepository.findById(id)
                .map(orderDTOMapper)
                .orElseThrow(() -> {
                    log.error("Order with ID {} not found", id);
                    return new OrderNotFoundException(id);
                });
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        log.info("Fetching all orders from the database");
        return orderRepository.findAll().stream().map(orderDTOMapper).toList();

    }

    @Override
    public void DeleteOrder(UUID id) {
        log.info("Deleting order with id {}",id);
        orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException(id));

        orderRepository.deleteById(id);
    }

    @Override
    public OrderDTO updateOrder(UUID id, OrderRequest request) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        User publisher = userRepository.findById(request.getPublisherId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        order.setPublisher(publisher);
        order.setTruckType(request.getTruckType());
        order.setLoadLocation(request.getLoadLocation());
        order.setDeliveryLocation(request.getDeliveryLocation());
        order.setWeight(request.getWeight());
        order.setVolume(request.getVolume());
        order.setPrice(request.getPrice());

        orderRepository.save(order);

        return orderDTOMapper.apply(order);
    }

    @Override
    public OrderDTO updateOrderStatus(UUID id, OrderStatus status) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException(id));

        order.setStatus(status);
        return orderDTOMapper.apply(orderRepository.save(order));
    }
}
