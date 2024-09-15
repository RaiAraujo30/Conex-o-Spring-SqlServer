package com.example.demo.controller;

import com.example.demo.models.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping()
    public String listClientes(Model model) {
        List<Cliente> clientes = clienteRepository.findAll();
        model.addAttribute("clientes", clientes);
        return "clientes";
    }

    @GetMapping("/consulta9")
    public String listClientesPercentual(Model model) {
        List<Object[]> clientes = clienteRepository.findClientesByPedidos();
        model.addAttribute("consulta9", clientes);
        return "consulta9";
    }

    @GetMapping("/consulta2")
    public String listClientesAmericanos(Model model) {
        List<Object[]> clientes = clienteRepository.findClientesByCategoria();
        model.addAttribute("consulta2", clientes);
        return "consulta2";
    }
}
