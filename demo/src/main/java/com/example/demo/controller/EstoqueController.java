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
import com.example.demo.models.Armazem;
import com.example.demo.models.Produto;
import com.example.demo.service.EstoqueService;
import com.example.demo.service.ArmazemService;
import com.example.demo.service.ProdutoService;

@RequestMapping("/estoques")
@Controller
public class EstoqueController {
    
    @Autowired
    private EstoqueService estoqueService;
    
    @Autowired
    private ArmazemService armazemService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping()
    public String listEstoques(Model model) {
        List<Estoque> estoques = estoqueService.findAll();
        model.addAttribute("estoques", estoques);
        return "estoques";
    }

    @GetMapping("/create")
    public String createEstoque(Model model) {
        model.addAttribute("estoque", new Estoque());

        // Adiciona a lista de armazéns e produtos para o formulário
        List<Armazem> armazens = armazemService.findAll();
        List<Produto> produtos = produtoService.findAll();
        model.addAttribute("armazens", armazens);
        model.addAttribute("produtos", produtos);

        return "Estoque/createEstoque";
    }

    @PostMapping("/create")
    public String createEstoque(@ModelAttribute("estoque") Estoque estoque, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Recarrega os armazéns e produtos em caso de erro
            model.addAttribute("armazens", armazemService.findAll());
            model.addAttribute("produtos", produtoService.findAll());
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

        // Adiciona a lista de armazéns e produtos para o formulário de atualização
        List<Armazem> armazens = armazemService.findAll();
        List<Produto> produtos = produtoService.findAll();
        model.addAttribute("armazens", armazens);
        model.addAttribute("produtos", produtos);
        model.addAttribute("estoque", estoque);

        return "Estoque/updateEstoque";
    }

    @PostMapping("/update")
    public String updateEstoque(@ModelAttribute("estoque") Estoque estoque, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Recarrega os armazéns e produtos em caso de erro
            model.addAttribute("armazens", armazemService.findAll());
            model.addAttribute("produtos", produtoService.findAll());
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
