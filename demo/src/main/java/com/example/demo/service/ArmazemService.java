package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.models.Armazem;
import com.example.demo.repository.ArmazemRepository;

public class ArmazemService {

    @Autowired
    private ArmazemRepository armazemRepository;

    //save
    public void save(Armazem armazem) {
        armazemRepository.save(armazem);
    }

    //deleteById
    public void deleteById(Long id) {
        armazemRepository.deleteById(id);
    }

    //findAll
    public List<Armazem> findAll() {
        return armazemRepository.findAll();
    }

    //findById
    public Armazem findById(Long id) {
        return armazemRepository.findById(id).get();
    }

    public List<Object[]> getArmazensByElectronics() {
        return armazemRepository.findArmazensByElectronics();
    }
}
