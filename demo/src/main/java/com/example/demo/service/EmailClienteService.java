package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.models.EmailCliente;
import com.example.demo.repository.EmailClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class EmailClienteService {
    
    @Autowired
    private EmailClienteRepository emailClienteRepository;

    //save
    public void save(EmailCliente emailCliente) {
        emailClienteRepository.save(emailCliente);
    }

    //deleteById
    public void deleteById(Long id) {
        emailClienteRepository.deleteById(id);
    }

    //findById
    public EmailCliente findById(Long id) {
        return emailClienteRepository.findById(id).get();
    }

    //findAll
    public List<EmailCliente> findAll() {
        return emailClienteRepository.findAll();
    }

}
