package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.models.Categoria;
import com.example.demo.repository.CategoriaRepository;

public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    //save
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    //findAll
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    //deleteById
    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }

    //findById
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).get();
    }

    
}
