package com.example.diploma.Service.impl;

import com.example.diploma.Entity.enums.RoleName;
import com.example.diploma.Entity.user.Role;
import com.example.diploma.Entity.user.User;
import com.example.diploma.Repository.RoleRepository;
import com.example.diploma.Repository.UserRepository;
import com.example.diploma.Service.UserService;
import com.example.diploma.dto.UserDTO;
import com.example.diploma.dto.UserRequest;
import com.example.diploma.exceptions.FieldAlreadyExistsException;
import com.example.diploma.mappers.UserDTOMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final static String USERNAME_FIELD = "username";
    private final static String EMAIL_FIELD = "email";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDTOMapper userDTOMapper;

    @Override
    public UserDTO save(UserRequest userRequest) {
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setUsername(userRequest.getUsername());
        user.setName(userRequest.getName());
        user.setLastName(userRequest.getLastName());
        user.setRating(userRequest.getRating());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        if (userRepository.findByUsername(userRequest.getUsername()) != null) {
            throw new FieldAlreadyExistsException(USERNAME_FIELD, userRequest.getUsername());
        } else if (userRepository.findByEmail(userRequest.getEmail()) != null) {
            throw new FieldAlreadyExistsException(EMAIL_FIELD, userRequest.getEmail());
        }

        log.info("Saving new user {} to the database", user.getUsername());
        return userDTOMapper.apply(userRepository.save(user));

    }

    @Override
    public Role save(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(UUID id, String roleName) {
        log.info("Adding new role {} to the user {}", roleName, id);
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
        Role role = roleRepository.findByName(RoleName.valueOf(roleName));
        if (!user.getRoles().contains(role)){
            user.getRoles().add(role);
        }else {
            throw new RuntimeException(String.format("user '%s' already has a role named '%s'", id, roleName));
        }

    }

    @Override
    public User get(UUID id) {
        log.info("Fetching user {}", id);
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
    }

    @Override
    public List<UserDTO> getAll() {
        log.info("Fetching all users from database");
        return userRepository.findAll().stream().map(userDTOMapper).toList();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            log.info("username {} not found", username);
            throw new UsernameNotFoundException("user not found");
        }else{
            log.info("username {} found in the database", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
