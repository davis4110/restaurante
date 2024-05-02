package com.restaurante.infrastructure.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "owner")
@Data
@NoArgsConstructor
public class OwnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(unique = true)
    private String identification;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String cellphone;
    private LocalDateTime dateCreated;
    
    @OneToMany
    private List<RestaurantEntity> restaurantEntity;

}
