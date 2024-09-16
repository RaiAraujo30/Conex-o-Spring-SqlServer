package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.models.ProdutoTraducao;
import com.example.demo.repository.ProdutoTraducaoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoTraducaoService {
    
    @Autowired
    private ProdutoTraducaoRepository produtoTraducaoRepository;

    //save
    public void save(ProdutoTraducao produtoTraducao) {
        produtoTraducaoRepository.save(produtoTraducao);
    }

    //deleteById
    public void deleteById(Long id) {
        produtoTraducaoRepository.deleteById(id);
    }

    //findAll
    public List<ProdutoTraducao> findAll() {
        return produtoTraducaoRepository.findAll();
    }
    
}
