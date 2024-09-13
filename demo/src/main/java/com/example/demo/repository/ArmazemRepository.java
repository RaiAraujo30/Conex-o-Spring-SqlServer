package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Armazem;

public interface ArmazemRepository extends JpaRepository<Armazem, Long> {
    
}
