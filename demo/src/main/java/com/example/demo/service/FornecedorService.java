package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.models.Fornecedor;
import com.example.demo.repository.FornecedorRepository;

public class FornecedorService {
    
    @Autowired
    private FornecedorRepository fornecedorRepository;

    //save
    public void save(Fornecedor fornecedor) {
        fornecedorRepository.save(fornecedor);
    }

    //findAll
    public List<Fornecedor> findAll() {
        return fornecedorRepository.findAll();
    }

    //deleteById
    public void deleteById(Long id) {
        fornecedorRepository.deleteById(id);
    }

    //findById
    public Fornecedor findById(Long id) {
        return fornecedorRepository.findById(id).get();
    }
}
