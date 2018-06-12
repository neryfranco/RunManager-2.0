package br.edu.ifsudestemg.corrida.controller;


import br.edu.ifsudestemg.corrida.model.Percurso;
import br.edu.ifsudestemg.corrida.model.Corrida;
import br.edu.ifsudestemg.corrida.repository.PercursoRepository;
import br.edu.ifsudestemg.corrida.repository.CorridaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "corrida")
public class CorridaController {

    @Autowired
    private CorridaRepository corridaRepository;
    @Autowired
    private PercursoRepository percursoRepository;

    @GetMapping(value = "")
    public String corridas(Model model){
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista de corridas");
        return "corrida/corridas";
    }

    @GetMapping(value = "add")
    public String getCorridasAdd(Model model){
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("title", "Adicionar corrida");
        model.addAttribute("percursos", percursoRepository.findAll());
        return "corrida/add";
    }

    @PostMapping(value = "add")
    public String postCorridasAdd(Model model, @ModelAttribute Corrida corrida){
        model.addAttribute("title", "Adicionar corrida");
        corridaRepository.save(corrida);
        return "redirect:/corrida";
    }

    @GetMapping(value = "edit/{id}")
    public String getCorridaEdit(Model model, @PathVariable Integer id) {
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar corrida");
        model.addAttribute("corridas", percursoRepository.findAll());
        Optional<Corrida> corrida = corridaRepository.findById(id);
        if (corrida.isPresent()){
            model.addAttribute("corrida", corrida.get());
        }
        return "corrida/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String postCorridaEdit(@ModelAttribute Corrida corrida, Model model,
                               @PathVariable Integer id, @PathVariable Percurso percurso) throws Exception {
        if (id.equals(corrida.getId())) {
            corridaRepository.save(corrida);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/corrida";
    }

    @GetMapping(value = "delete/{id}")
    public String getCorridaDelete(Model model, @PathVariable Integer id) {
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir corrida");
        Optional<Corrida> corrida = corridaRepository.findById(id);
        if (corrida.isPresent()) {
            model.addAttribute("corrida", corrida.get());
        }

        return "corrida/delete";
    }

    @PostMapping(value = "delete/{id}")
    public String postCorridaDelete(@PathVariable Integer id, @ModelAttribute Corrida corrida) {
        corridaRepository.delete(corrida);
        return "redirect:/corrida";
    }
}