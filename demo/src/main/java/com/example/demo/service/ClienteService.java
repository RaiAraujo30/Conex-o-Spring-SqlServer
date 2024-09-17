package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.models.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    //save
    public  void save(Cliente cliente){
        clienteRepository.save(cliente);
    }

    //findAll
    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    //deleteById
    public void deleteById(Long id){
        clienteRepository.deleteById(id);
    }

    //findById
    public Cliente findById(Long id){
        return clienteRepository.findById(id).get();
    }
    
    //findClientesByPedidos
    public List<Object[]> getClientesByPedidos() {
        return clienteRepository.findClientesByPedidos();
    }

    //findClientesAmericanos
    public List<Object[]> getClientesAmericanos() {
        return clienteRepository.findClientesAmericanos();
    }
    
}
