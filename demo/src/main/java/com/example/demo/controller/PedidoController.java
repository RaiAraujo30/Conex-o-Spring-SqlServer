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

import com.example.demo.models.Pedido;
import com.example.demo.service.PedidoService;

@RequestMapping("/pedidos")
@Controller
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;

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
    public String createPedido(Model model) {
        model.addAttribute("pedido", new Pedido());
        return "Pedido/createPedido";
    }

    @PostMapping("/create")
    public String createPedido(@ModelAttribute("pedido") Pedido pedido, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Pedido/createPedido";
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
        model.addAttribute("pedido", pedido);
        return "Pedido/updatePedido";
    }

    @PostMapping("/update")
    public String updatePedido(@ModelAttribute("pedido") Pedido pedido, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Pedido/updatePedido";
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
