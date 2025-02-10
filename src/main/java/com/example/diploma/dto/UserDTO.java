package com.example.diploma.dto;

import com.example.diploma.Entity.user.Role;

import java.util.List;
import java.util.UUID;

public record UserDTO(UUID id, String username, String name, String lastName, String email, double rating, List<Role> roles) {

}
