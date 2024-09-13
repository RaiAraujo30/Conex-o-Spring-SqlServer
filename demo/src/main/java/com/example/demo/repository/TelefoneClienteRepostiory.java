package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.TelefoneCliente;

public interface TelefoneClienteRepostiory extends JpaRepository<TelefoneCliente, Long> {
    
}
