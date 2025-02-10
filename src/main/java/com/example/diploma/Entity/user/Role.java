package com.example.diploma.Entity.user;

import com.example.diploma.Entity.enums.RoleName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.UUID;

import static jakarta.persistence.EnumType.*;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {

    @Id
    @Column
    @GeneratedValue
    private UUID id;

    @Column
    @Enumerated(STRING)
    private RoleName name;
}
