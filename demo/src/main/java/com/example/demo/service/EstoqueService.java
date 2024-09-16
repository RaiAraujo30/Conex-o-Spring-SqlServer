package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.models.Estoque;
import com.example.demo.repository.EstoqueRepository;

public class EstoqueService {
    
    @Autowired
    private EstoqueRepository estoqueRepository;

    //save
    public void save(Estoque estoque) {
        estoqueRepository.save(estoque);
    }

    //deleteById
    public void deleteById(Long id) {
        estoqueRepository.deleteById(id);
    }

    //findAll
    public List<Estoque> findAll() {
        return estoqueRepository.findAll();
    }

    //findById
    public Estoque findById(Long id) {
        return estoqueRepository.findById(id).get();
    }
}
