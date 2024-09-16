package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.models.ItemPedido;
import com.example.demo.repository.ItemPedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {
    
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    //save
    public  void save(ItemPedido itemPedido){
        itemPedidoRepository.save(itemPedido);
    }

    //findAll
    public List<ItemPedido> findAll(){
        return itemPedidoRepository.findAll();
    }

    //deleteById
    public void deleteById(Long id){
        itemPedidoRepository.deleteById(id);
    }

    //findById
    public ItemPedido findById(Long id){
        return itemPedidoRepository.findById(id).get();
    }
    
}
