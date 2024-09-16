package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.models.Pedido;
import com.example.demo.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    //save
    public void save(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    //deleteById
    public void deleteById(Long id) {
        pedidoRepository.deleteById(id);
    }

    //findById
    public Pedido findById(Long id) {
        return pedidoRepository.findById(id).get();
    }

    //findAll
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }
    
    //findPedidosByCliente
    public List<Object[]> getPedidosByCliente() {
        return pedidoRepository.findPedidosByCliente();
    }

}
