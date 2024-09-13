package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.ProdutoTraducao;

public interface ProdutoTraducaoRepository extends JpaRepository<ProdutoTraducao, Long> {
    
}
