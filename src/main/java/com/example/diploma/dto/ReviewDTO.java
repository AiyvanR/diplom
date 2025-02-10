package com.example.diploma.dto;

import com.example.diploma.Entity.Order;
import com.example.diploma.Entity.user.User;

public record ReviewDTO(User reviewer, User reviewedUser, Order order, int rating, String comment) {
}
