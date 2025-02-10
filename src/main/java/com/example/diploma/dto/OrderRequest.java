package com.example.diploma.dto;

import com.example.diploma.Entity.enums.OrderStatus;
import com.example.diploma.Entity.user.User;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderRequest {

    @NotNull(message = "Invalid userId:id must not be null")
    private UUID publisherId;

    @NotNull(message = "Invalid truckType: truckType must be not null")
    private String truckType;

    @NotNull(message = "Invalid loadLocation: loadLocation must be not null")
    private String loadLocation;

    @NotNull(message = "Invalid deliveryLocation: deliveryLocation must be not null")
    private String deliveryLocation;

    @NotNull(message = "Invalid weight: weight must be not null")
    private double weight;

    private double volume;

    @NotNull(message = "Invalid price: price must be not null")
    private double price;

    private OrderStatus status;

    private List<UUID> listOfImagesId;


}
