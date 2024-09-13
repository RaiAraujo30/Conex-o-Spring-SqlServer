package com.example.demo.controller;

import com.example.demo.models.EmailCliente;
import com.example.demo.repository.EmailClienteRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EmailClienteController {

    @Autowired
    private EmailClienteRepository emailClienteRepository;

    @GetMapping("/email-clientes/{id}")
    public String getEmailCliente(@PathVariable Long id, Model model) {
        List<EmailCliente> emailClientes = emailClienteRepository.findAll();
        model.addAttribute("emailClientes", emailClientes);
        return "emailCliente";
    }
}
