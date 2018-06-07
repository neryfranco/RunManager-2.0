package br.edu.ifsudestemg.corrida.controller;


import br.edu.ifsudestemg.corrida.model.Administrador;
import br.edu.ifsudestemg.corrida.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "administrador")
public class AdministradorController {

    @Autowired
    private AdministradorRepository administradorRepository;

    @GetMapping(value = "")
    public String administradores(Model model){
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista de administradores");
        model.addAttribute("administradores", administradorRepository.findAll());
        return "administrador/administradores";
    }

    @GetMapping(value = "add")
    public String getAdministradoresAdd(Model model){
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("title", "Adicionar administrador");
        return "administrador/add";
    }

    @PostMapping(value = "add")
    public String postAdministradoresAdd(Model model, @ModelAttribute Administrador administrador){
        model.addAttribute("title", "Adicionar administrador");
        administradorRepository.save(administrador);
        return "redirect:/administrador";
    }

    @GetMapping(value = "edit/{cpf}")
    public String getAdministradorEdit(Model model, @PathVariable String cpf) {
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar administrador");
        Optional<Administrador> administrador = administradorRepository.findById(cpf);
        if (administrador.isPresent()){
            model.addAttribute("administrador", administrador.get());
        }
        return "administrador/edit";
    }

    @PostMapping(value = "edit/{cpf}")
    public String postAdministradorEdit(@ModelAttribute Administrador administrador, Model model,
                                  @PathVariable String cpf) throws Exception {
        if (cpf.equals(administrador.getCpf())) {
            administradorRepository.save(administrador);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/administrador";
    }

    @GetMapping(value = "delete/{cpf}")
    public String getAdministradorDelete(Model model, @PathVariable String cpf) {
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir administrador");
        Optional<Administrador> administrador = administradorRepository.findById(cpf);
        if (administrador.isPresent()) {
            model.addAttribute("administrador", administrador.get());
        }

        return "administrador/delete";
    }

    @PostMapping(value = "delete/{cpf}")
    public String postAdministradorDelete(@PathVariable String cpf, @ModelAttribute Administrador administrador) {
        administradorRepository.delete(administrador);
        return "redirect:/administrador";
    }

}