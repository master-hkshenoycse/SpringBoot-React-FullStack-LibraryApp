package com.reactapp.backend.springbootlibrary.dao;


import com.reactapp.backend.springbootlibrary.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment findByUserEmail(String userEmail);
}
