package com.example.demo.controller;

import com.example.demo.models.Armazem;
import com.example.demo.service.ArmazemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/armazens")
@Controller
public class ArmazemController {

    @Autowired
    private ArmazemService armazemService;

    @GetMapping("/consulta3")
    public String listArmazens(Model model) {
        List<Object[]> armazens = armazemService.getArmazensByElectronics();
        model.addAttribute("consulta3", armazens);
        return "consulta3";
    }

    @GetMapping("Create")
    public String createArmazem(Model model) {
        model.addAttribute("armazem", new Armazem());
        return "Armazem/createArmazem";
    }

    @PostMapping("Create")
    public String createArmazem(@ModelAttribute("armazem") Armazem armazem, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Armazem/createArmazem";
        }
        armazemService.save(armazem);

        return "redirect:/armazens";
    }

    // Atualizar um armazém existente
    @GetMapping("/update/{id}")
    public String updateArmazemForm(@PathVariable("id") Long id, Model model) {
        Armazem armazem = armazemService.findById(id);  
        if (armazem == null) {
            return "error/404";  
        }
        model.addAttribute("armazem", armazem);
        return "Armazem/updateArmazem";  // Renderiza o template de atualização
    }

    @PostMapping("/update")
    public String updateArmazem( @ModelAttribute("armazem") Armazem armazem, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Armazem/updateArmazem";  
        }
        armazemService.save(armazem); 
        return "redirect:/armazens"; 
    }

    // Deletar um armazém
    @GetMapping("/delete/{id}")
    public String deleteArmazem(@PathVariable("id") Long id, Model model) {
        Armazem armazem = armazemService.findById(id);  // Busca o armazém pelo ID
        if (armazem == null) {
            return "error/404";  // Retorna página de erro se o armazém não for encontrado
        }
        model.addAttribute("armazem", armazem);
        return "Armazem/deleteArmazem";  // Renderiza o template de confirmação de exclusão
    }

    @PostMapping("/delete/{id}")
    public String confirmDeleteArmazem(@PathVariable("id") Long id) {
        armazemService.deleteById(id);  // Exclui o armazém pelo ID
        return "redirect:/armazens";  // Redireciona para a lista de armazéns
    }
}
