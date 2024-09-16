package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.models.Produto;
import com.example.demo.repository.ProdutoRepository;

public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    //save
    public void save(Produto produto) {
        produtoRepository.save(produto);
    }

    //deleteById
    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }

    //findById
    public Produto findById(Long id) {
        return produtoRepository.findById(id).get();
    }

    //findAll
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    //findProductsByarmazens
    public List<Object[]> getProductsByarmazens() {
        return produtoRepository.findProductsByarmazens();
    }

}