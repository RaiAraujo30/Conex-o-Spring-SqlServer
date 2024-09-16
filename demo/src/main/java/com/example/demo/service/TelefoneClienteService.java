package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.models.TelefoneCliente;
import com.example.demo.repository.TelefoneClienteRepostiory;
import org.springframework.stereotype.Service;

@Service
public class TelefoneClienteService {
    
    @Autowired
    private TelefoneClienteRepostiory telefoneClienteRepository;

    //save
    public void save(TelefoneCliente telefoneCliente) {
        telefoneClienteRepository.save(telefoneCliente);
    }

    //deleteById
    public void deleteById(Long id) {
        telefoneClienteRepository.deleteById(id);
    }

    //findAll
    public List<TelefoneCliente> findAll() {
        return telefoneClienteRepository.findAll();
    }

    //findById
    public TelefoneCliente findById(Long id) {
        return telefoneClienteRepository.findById(id).get();
    }
    
}
