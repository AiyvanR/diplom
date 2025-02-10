package com.example.diploma.dto;

import com.example.diploma.Entity.Image;
import com.example.diploma.Entity.enums.OrderStatus;
import com.example.diploma.Entity.user.User;

import java.util.List;
import java.util.UUID;

public record OrderDTO(UUID id, User publisher, String truckType, String loadLocation,
                       String deliveryLocation, double weight, double volume, double price, OrderStatus status, List<ImageDTO> images) {
}
