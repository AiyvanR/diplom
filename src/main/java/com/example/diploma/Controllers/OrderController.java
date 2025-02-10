package com.example.diploma.Controllers;

import com.example.diploma.Entity.Order;
import com.example.diploma.Service.OrderService;
import com.example.diploma.dto.OrderDTO;
import com.example.diploma.dto.OrderRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO save(@Valid @RequestBody OrderRequest request){
        return orderService.create(request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable UUID id){
        orderService.DeleteOrder(id);
    }

    @GetMapping("/getAll")
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable UUID id){
        return orderService.getOrderById(id);
    }

    @PutMapping("/update/{id}")
    public OrderDTO updateOrder(@PathVariable UUID id, @RequestBody OrderRequest request){
        return orderService.updateOrder(id,request);
    }


}
