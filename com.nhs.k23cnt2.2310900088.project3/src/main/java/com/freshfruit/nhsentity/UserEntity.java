package com.freshfruit.nhsentity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(unique = true)
    private String username;

    private String password;

    private String full_name;
    private String email;
    private String phone;
    private String address;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;   // <-- phải là RoleEntity
}
