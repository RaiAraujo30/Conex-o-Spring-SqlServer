package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Estoque;
import com.example.demo.service.EstoqueService;

@RequestMapping("/estoques")
@Controller
public class EstoqueController {
    
    @Autowired
    private EstoqueService estoqueService;

    @GetMapping()
    public String listEstoques(Model model) {
        List<Estoque> estoques = estoqueService.findAll();
        model.addAttribute("estoques", estoques);
        return "estoques";
    }

    @GetMapping("/create")
    public String createEstoque(Model model) {
        model.addAttribute("estoque", new Estoque());
        return "Estoque/createEstoque";
    }

    @PostMapping("/create")
    public String createEstoque(@ModelAttribute("estoque") Estoque estoque, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Estoque/createEstoque";
        }
        estoqueService.save(estoque);
        return "redirect:/estoques";
    }

    @GetMapping("/update/{id}")
    public String updateEstoqueForm(@PathVariable("id") Long id, Model model) {
        Estoque estoque = estoqueService.findById(id);
        if (estoque == null) {
            return "error/404";
        }
        model.addAttribute("estoque", estoque);
        return "Estoque/updateEstoque";
    }

    @PostMapping("/update")
    public String updateEstoque(@ModelAttribute("estoque") Estoque estoque, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Estoque/updateEstoque";
        }
        estoqueService.save(estoque);
        return "redirect:/estoques";
    }

    @GetMapping("/delete/{id}")
    public String deleteEstoque(@PathVariable("id") Long id) {
        Estoque estoque = estoqueService.findById(id);
        if (estoque == null) {
            return "error/404";
        }
        estoqueService.deleteById(id);
        return "redirect:/estoques";
    }
}
