package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Pedido;
import com.example.demo.models.ItemPedido;
import com.example.demo.models.Produto;
import com.example.demo.models.Cliente;
import com.example.demo.service.PedidoService;
import com.example.demo.service.ClienteService;
import com.example.demo.service.ProdutoService;

@RequestMapping("/pedidos")
@Controller
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping()
    public String listPedidos(Model model) {
        List<Pedido> pedidos = pedidoService.findAll();
        model.addAttribute("pedidos", pedidos);
        return "pedidos";
    }

    @GetMapping("/consulta6")
    public String listPedidosByCliente(Model model) {
        List<Object[]> resultado = pedidoService.getPedidosByCliente();
        model.addAttribute("consulta6", resultado);
        return "consulta6";
    }

    @GetMapping("/create")
    public String createPedidoForm(Model model) {
        model.addAttribute("pedido", new Pedido());

        // Adiciona a lista de clientes e produtos ao formulário
        List<Cliente> clientes = clienteService.findAll();
        model.addAttribute("clientes", clientes);

        List<Produto> produtos = produtoService.findAll();
        model.addAttribute("produtos", produtos);

        return "Pedido/createPedido";
    }

    @PostMapping("/create")
    public String createPedido(@ModelAttribute("pedido") Pedido pedido, BindingResult bindingResult,
                               @RequestParam("produtos") List<Long> produtosIds, 
                               @RequestParam("quantidades") List<Integer> quantidades, 
                               Model model) {
        if (bindingResult.hasErrors()) {
            // Recarrega clientes e produtos em caso de erro
            model.addAttribute("clientes", clienteService.findAll());
            model.addAttribute("produtos", produtoService.findAll());
            return "Pedido/createPedido";
        }

        // Adiciona os itens de pedido ao pedido
        for (int i = 0; i < produtosIds.size(); i++) {
            Produto produto = produtoService.findById(produtosIds.get(i));
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(quantidades.get(i));
            itemPedido.setPreco(produto.getPrecoVenda());
            pedido.getItensPedido().add(itemPedido);
        }

        pedidoService.save(pedido);
        return "redirect:/pedidos";
    }

    @GetMapping("/update/{id}")
    public String updatePedidoForm(@PathVariable("id") Long id, Model model) {
        Pedido pedido = pedidoService.findById(id);
        if (pedido == null) {
            return "error/404";
        }

        // Adiciona a lista de clientes e produtos para o formulário de atualização
        List<Cliente> clientes = clienteService.findAll();
        List<Produto> produtos = produtoService.findAll();
        model.addAttribute("clientes", clientes);
        model.addAttribute("produtos", produtos);
        model.addAttribute("pedido", pedido);

        return "Pedido/updatePedido";
    }

    @PostMapping("/update")
    public String updatePedido(@ModelAttribute("pedido") Pedido pedido, BindingResult bindingResult,
                               @RequestParam("produtos") List<Long> produtosIds, 
                               @RequestParam("quantidades") List<Integer> quantidades, 
                               Model model) {
        if (bindingResult.hasErrors()) {
            // Recarrega clientes e produtos em caso de erro
            model.addAttribute("clientes", clienteService.findAll());
            model.addAttribute("produtos", produtoService.findAll());
            return "Pedido/updatePedido";
        }

        // Atualiza os itens do pedido
        pedido.getItensPedido().clear();
        for (int i = 0; i < produtosIds.size(); i++) {
            Produto produto = produtoService.findById(produtosIds.get(i));
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(quantidades.get(i));
            itemPedido.setPreco(produto.getPrecoVenda());
            pedido.getItensPedido().add(itemPedido);
        }

        pedidoService.save(pedido);
        return "redirect:/pedidos";
    }

    @GetMapping("/delete/{id}")
    public String deletePedido(@PathVariable("id") Long id) {
        Pedido pedido = pedidoService.findById(id);
        if (pedido == null) {
            return "error/404";
        }
        pedidoService.deleteById(id);
        return "redirect:/pedidos";
    }
}
