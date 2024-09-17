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
import com.example.demo.models.Fornecedor;
import com.example.demo.models.Categoria;
import com.example.demo.service.ProdutoService;
import com.example.demo.service.FornecedorService;
import com.example.demo.service.CategoriaService;

@RequestMapping("/produtos")
@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/produtos")
    public String listProducts(Model model) {
        List<Produto> produtos = produtoService.findAll();
        model.addAttribute("produtos", produtos);
        return "produtos";
    }

    @GetMapping("/consulta1")
    public String listProductsFornecedorJapao(Model model) {
        List<Object[]> produtos = produtoService.getProductsByFornecedor();
        model.addAttribute("consulta1", produtos);
        return "consulta1";
    }

    @GetMapping("/consulta4")
    public String listProductsQuantidadeMedia(Model model) {
        List<Object[]> produtos = produtoService.getProductsByEstoque();
        model.addAttribute("consulta4", produtos);
        return "consulta4";
    }

    @GetMapping("/consulta7")
    public String listProductsArmazens(Model model) {
        List<Object[]> produtosArmazens = produtoService.getProductsByarmazens();
        model.addAttribute("consulta7", produtosArmazens);
        return "consulta7";
    }

    @GetMapping("/consulta8")
    public String listProductsPreco(Model model) {
        List<Object[]> produtos = produtoService.getProductsByPrecoVenda();
        model.addAttribute("consulta8", produtos);
        return "consulta8";
    }

    @GetMapping("/create")
    public String createProduto(Model model) {
        model.addAttribute("produto", new Produto());

        // Adiciona a lista de fornecedores e categorias ao formulário
        List<Fornecedor> fornecedores = fornecedorService.findAll();
        List<Categoria> categorias = categoriaService.findAll();
        model.addAttribute("fornecedores", fornecedores);
        model.addAttribute("categorias", categorias);

        return "Produto/createProduto";
    }

    @PostMapping("/create")
    public String createProduto(@ModelAttribute("produto") Produto produto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Recarrega as listas em caso de erro
            model.addAttribute("fornecedores", fornecedorService.findAll());
            model.addAttribute("categorias", categoriaService.findAll());
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

        // Adiciona a lista de fornecedores e categorias ao formulário de atualização
        List<Fornecedor> fornecedores = fornecedorService.findAll();
        List<Categoria> categorias = categoriaService.findAll();
        model.addAttribute("fornecedores", fornecedores);
        model.addAttribute("categorias", categorias);
        model.addAttribute("produto", produto);

        return "Produto/updateProduto";
    }

    @PostMapping("/update")
    public String updateProduto(@ModelAttribute("produto") Produto produto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Recarrega as listas em caso de erro
            model.addAttribute("fornecedores", fornecedorService.findAll());
            model.addAttribute("categorias", categoriaService.findAll());
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
