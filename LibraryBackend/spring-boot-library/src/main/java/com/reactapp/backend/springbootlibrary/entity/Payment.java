package com.reactapp.backend.springbootlibrary.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Columns;

@Entity
@Table(name="payment")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @Column(name="user_email")
    private String userEmail;

    @Column(name="amount")
    private double amount;
}
