package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.repository.PedidoRepository;

@Controller
public class PedidoController {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("/consulta6")
    public String listProducts(Model model) {
        List<Object[]> resultado = pedidoRepository.findPedidosByCliente();
        model.addAttribute("consulta6", resultado);
        return "consulta6";
    }
}
