package com.smart.smartApi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @NotNull
    @Column(name = "password", nullable = false, length = Integer.MAX_VALUE)
    private String password;

    @Size(max = 20)
    @NotNull
    @Column(name = "role", nullable = false, length = 20)
    private String role;

    @OneToMany(mappedBy = "user")
    private Set<CartItem> cartItems = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Order> orders = new LinkedHashSet<>();


}