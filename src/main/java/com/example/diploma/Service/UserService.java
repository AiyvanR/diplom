package com.example.diploma.Service;

import com.example.diploma.Entity.user.Role;
import com.example.diploma.Entity.user.User;
import com.example.diploma.dto.UserDTO;
import com.example.diploma.dto.UserRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDTO save(UserRequest userRequest);
    Role save(Role role);
    void addRoleToUser(UUID id, String roleName);
    User get(UUID id);
    List<UserDTO> getAll();
}
