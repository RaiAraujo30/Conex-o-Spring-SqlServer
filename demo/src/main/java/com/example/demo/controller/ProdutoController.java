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

import com.example.demo.models.Produto;
import com.example.demo.service.ProdutoService;

@RequestMapping("/produtos")
@Controller
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;

    @GetMapping()
    public String listProducts(Model model) {
        List<Produto> produtos = produtoService.findAll();
        model.addAttribute("produtos", produtos);
        return "produtos";
    }

    @GetMapping("/consulta7")
    public String listProductsArmazens(Model model) {
        List<Object[]> produtosArmazens = produtoService.getProductsByarmazens();
        model.addAttribute("consulta7", produtosArmazens);
        return "consulta7";
    }

    @GetMapping("/create")
    public String createProduto(Model model) {
        model.addAttribute("produto", new Produto());
        return "Produto/createProduto";
    }

    @PostMapping("/create")
    public String createProduto(@ModelAttribute("produto") Produto produto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Produto/createProduto";
        }
        produtoService.save(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/update/{id}")
    public String updateProdutoForm(@PathVariable("id") Long id, Model model) {
        Produto produto = produtoService.findById(id);
        if (produto == null) {
            return "error/404";
        }
        model.addAttribute("produto", produto);
        return "Produto/updateProduto";
    }

    @PostMapping("/update")
    public String updateProduto(@ModelAttribute("produto") Produto produto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Produto/updateProduto";
        }
        produtoService.save(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduto(@PathVariable("id") Long id) {
        Produto produto = produtoService.findById(id);
        if (produto == null) {
            return "error/404";
        }
        produtoService.deleteById(id);
        return "redirect:/produtos";
    }
}
