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

import com.example.demo.models.Fornecedor;
import com.example.demo.service.FornecedorService;

@RequestMapping("/fornecedores")
@Controller
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping()
    public String listFornecedores(Model model) {
        List<Fornecedor> fornecedores = fornecedorService.findAll();
        model.addAttribute("fornecedores", fornecedores);
        return "fornecedores";
    }

    @GetMapping("/create")
    public String createFornecedor(Model model) {
        model.addAttribute("fornecedor", new Fornecedor());
        return "Fornecedor/createFornecedor";
    }

    @PostMapping("/create")
    public String createFornecedor(@ModelAttribute("fornecedor") Fornecedor fornecedor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Fornecedor/createFornecedor";
        }
        fornecedorService.save(fornecedor);
        return "redirect:/fornecedores";
    }

    @GetMapping("/update/{id}")
    public String updateFornecedorForm(@PathVariable("id") Long id, Model model) {
        Fornecedor fornecedor = fornecedorService.findById(id);
        if (fornecedor == null) {
            return "error/404";
        }
        model.addAttribute("fornecedor", fornecedor);
        return "Fornecedor/updateFornecedor";
    }

    @PostMapping("/update")
    public String updateFornecedor(@ModelAttribute("fornecedor") Fornecedor fornecedor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Fornecedor/updateFornecedor";
        }
        fornecedorService.save(fornecedor);
        return "redirect:/fornecedores";
    }

    @GetMapping("/delete/{id}")
    public String deleteFornecedor(@PathVariable("id") Long id) {
        Fornecedor fornecedor = fornecedorService.findById(id);
        if (fornecedor == null) {
            return "error/404";
        }
        fornecedorService.deleteById(id);
        return "redirect:/fornecedores";
    }
}
