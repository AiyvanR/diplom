package com.example.diploma.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "images")
public class Image {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String name;

    @Column
    private String type;

    @Lob
    @Column(length = 10485760)
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;



}
