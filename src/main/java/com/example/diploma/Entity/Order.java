package com.example.diploma.Entity;

import com.example.diploma.Entity.enums.OrderStatus;
import com.example.diploma.Entity.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private User publisher;

    @Column
    private String truckType;

    @Column
    private String loadLocation;


    @Column
    private String deliveryLocation;

    @Column
    private double weight;

    @Column
    private double volume;

    @Column
    private double price;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Image> images;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


}
