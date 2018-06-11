package br.edu.ifsudestemg.corrida.controller;


import br.edu.ifsudestemg.corrida.model.Organizador;
import br.edu.ifsudestemg.corrida.repository.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "organizador")
public class OrganizadorController {

    @Autowired
    private OrganizadorRepository organizadorRepository;

    @GetMapping(value = "")
    public String organizadores(Model model){
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista de organizadores");
        model.addAttribute("organizadores", organizadorRepository.findAll());
        return "organizador/organizadores";
    }

    @GetMapping(value = "add")
    public String getOrganizadoresAdd(Model model){
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("title", "Adicionar organizador");
        return "organizador/add";
    }

    @PostMapping(value = "add")
    public String postOrganizadoresAdd(Model model, @ModelAttribute Organizador organizador){
        model.addAttribute("title", "Adicionar organizador");
        organizadorRepository.save(organizador);
        return "redirect:/organizador";
    }

    @GetMapping(value = "edit/{cpf}")
    public String getOrganizadorEdit(Model model, @PathVariable String cpf) {
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar organizador");
        Optional<Organizador> organizador = organizadorRepository.findById(cpf);
        if (organizador.isPresent()){
            model.addAttribute("organizador", organizador.get());
        }
        return "organizador/edit";
    }

    @PostMapping(value = "edit/{cpf}")
    public String postOrganizadorEdit(@ModelAttribute Organizador organizador, Model model,
                                        @PathVariable String cpf) throws Exception {
        if (cpf.equals(organizador.getCpf())) {
            organizadorRepository.save(organizador);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/organizador";
    }

    @GetMapping(value = "delete/{cpf}")
    public String getOrganizadorDelete(Model model, @PathVariable String cpf) {
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir organizador");
        Optional<Organizador> organizador = organizadorRepository.findById(cpf);
        if (organizador.isPresent()) {
            model.addAttribute("organizador", organizador.get());
        }

        return "organizador/delete";
    }

    @PostMapping(value = "delete/{cpf}")
    public String postOrganizadorDelete(@PathVariable String cpf, @ModelAttribute Organizador organizador) {
        organizadorRepository.delete(organizador);
        return "redirect:/organizador";
    }
}