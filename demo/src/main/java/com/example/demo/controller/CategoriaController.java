package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.CategoriaService;


@RequestMapping("/categorias")
@Controller
public class CategoriaController {
    
    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/consulta5")
    public String listProductsFornecedorJapao(Model model) {
        
        List<Object[]> categoria = categoriaService.getCategoriaByFaturamento();
        model.addAttribute("consulta5", categoria);
        return "consulta5";
    }
    
}
