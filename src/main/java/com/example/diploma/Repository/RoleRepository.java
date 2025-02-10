package com.example.diploma.Repository;

import com.example.diploma.Entity.enums.RoleName;
import com.example.diploma.Entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByName(RoleName name);
}
