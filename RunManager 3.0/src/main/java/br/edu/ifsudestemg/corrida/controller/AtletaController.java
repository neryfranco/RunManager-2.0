package br.edu.ifsudestemg.corrida.controller;


import br.edu.ifsudestemg.corrida.model.Atleta;
import br.edu.ifsudestemg.corrida.repository.AtletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "atleta")
public class AtletaController {

    @Autowired
    private AtletaRepository atletaRepository;

    @GetMapping(value = "")
    public String atletas(Model model){
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista de atletas");
        model.addAttribute("atletas", atletaRepository.findAll());
        return "atleta/atletas";
    }

    @GetMapping(value = "add")
    public String getAtletasAdd(Model model){
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("title", "Adicionar atleta");
        return "atleta/atletas";
    }

    @PostMapping(value = "add")
    public String postAtletasAdd(Model model, @ModelAttribute Atleta atleta){
        model.addAttribute("tittle", "Adicionar atleta");
        atletaRepository.save(atleta);
        return "redirect:/atletas";
    }

    @GetMapping(value = "edit/{cpf}")
    public String getAtletaEdit(Model model, @PathVariable String cpf) {
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar atleta");
        Optional<Atleta> atleta = atletaRepository.findById(cpf);
        if (atleta.isPresent()){
            model.addAttribute("atleta", atleta.get());
        }
        return "atleta/atletas";
    }

    @PostMapping(value = "edit/{cpf}")
    public String postAtletaEdit(@ModelAttribute Atleta atleta, Model model,
                                        @PathVariable Long cpf) throws Exception {
        if (cpf.equals(atleta.getCpf())) {
            atletaRepository.save(atleta);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/atletas";
    }

    @GetMapping(value = "delete/{cpf}")
    public String getAtletaDelete(Model model, @PathVariable String cpf) {
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir atleta");
        Optional<Atleta> atleta = atletaRepository.findById(cpf);
        if (atleta.isPresent()) {
            model.addAttribute("atleta", atleta.get());
        }

        return "atleta/atletas";
    }

    @PostMapping(value = "delete/{cpf}")
    public String postAtletaDelete(@PathVariable Long cpf, @ModelAttribute Atleta atleta) {
        atletaRepository.delete(atleta);
        return "redirect:/atletas";
    }

}
