package com.example.demo.controller;

import com.example.demo.repository.ArmazemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ArmazemController {

    @Autowired
    private ArmazemRepository armazemRepository;

    @GetMapping("/consulta3")
    public String listArmazens(Model model) {
        List<Object[]> armazens = armazemRepository.findArmazensByElectronics();
        model.addAttribute("consulta3", armazens);
        return "consulta3";
    }
}
