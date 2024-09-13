package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.EmailCliente;

public interface EmailClienteRepository extends JpaRepository<EmailCliente, Long> {

}
