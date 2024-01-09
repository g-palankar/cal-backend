package com.cal.calbackend.user.model;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "registered_user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;
}
