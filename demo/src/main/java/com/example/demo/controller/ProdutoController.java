package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.models.Produto;
import com.example.demo.repository.ProdutoRepository;

@Controller
public class ProdutoController {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/produtos")
    public String listProducts(Model model) {
        List<Produto> produtos = produtoRepository.findAll();
        model.addAttribute("produtos", produtos);
        return "produtos";
    }

    @GetMapping("/consulta7")
    public String listProductsArmazens(Model model) {
        List<Object[]> produtosArmazens = produtoRepository.findProductsByarmazens();
        model.addAttribute("consulta7", produtosArmazens);
        return "consulta7";
    }
}
