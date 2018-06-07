package br.edu.ifsudestemg.corrida.controller;


import br.edu.ifsudestemg.corrida.model.Tapete;
import br.edu.ifsudestemg.corrida.repository.TapeteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "tapete")
public class TapeteController {

    @Autowired
    private TapeteRepository tapeteRepository;

    @GetMapping(value = "")
    public String tapetes(Model model){
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista de tapetes");
        model.addAttribute("tapetes", tapeteRepository.findAll());
        return "tapete/tapetes";
    }

    @GetMapping(value = "add")
    public String getTapeteesAdd(Model model){
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("title", "Adicionar tapete");
        return "tapete/add";
    }

    @PostMapping(value = "add")
    public String postTapeteesAdd(Model model, @ModelAttribute Tapete tapete){
        model.addAttribute("title", "Adicionar tapete");
        tapeteRepository.save(tapete);
        return "redirect:/tapete";
    }

    @GetMapping(value = "edit/{id}")
    public String getTapeteEdit(Model model, @PathVariable Integer id) {
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar tapete");
        Optional<Tapete> tapete = tapeteRepository.findById(id);
        if (tapete.isPresent()){
            model.addAttribute("tapete", tapete.get());
        }
        return "tapete/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String postTapeteEdit(@ModelAttribute Tapete tapete, Model model,
                                        @PathVariable Integer id) throws Exception {
        if (id.equals(tapete.getId())) {
            tapeteRepository.save(tapete);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/tapete";
    }

    @GetMapping(value = "delete/{id}")
    public String getTapeteDelete(Model model, @PathVariable Integer id) {
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir tapete");
        Optional<Tapete> tapete = tapeteRepository.findById(id);
        if (tapete.isPresent()) {
            model.addAttribute("tapete", tapete.get());
        }

        return "tapete/delete";
    }

    @PostMapping(value = "delete/{id}")
    public String postTapeteDelete(@PathVariable Integer id, @ModelAttribute Tapete tapete) {
        tapeteRepository.delete(tapete);
        return "redirect:/tapete";
    }
}