package com.example.diploma.Controllers;

import com.example.diploma.Entity.user.Role;
import com.example.diploma.Repository.UserRepository;
import com.example.diploma.Service.UserService;
import com.example.diploma.dto.UserDTO;
import com.example.diploma.dto.UserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping()
    public List<UserDTO> getAll(){
        return userService.getAll();
    }

    @PostMapping("/save")
    public UserDTO saveUser(@RequestBody @Valid UserRequest request){
        return userService.save(request);
    }


    @PostMapping("/add-role")
    public Role createRole(@RequestBody Role role){
        return userService.save(role);
    }

    @PostMapping("/add-role-to-user")
    public void addRoleToUser(@RequestParam UUID id, @RequestParam String roleName){
        userService.addRoleToUser(id, roleName);
    }
}
