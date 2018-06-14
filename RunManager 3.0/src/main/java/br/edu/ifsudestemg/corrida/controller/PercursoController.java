package br.edu.ifsudestemg.corrida.controller;


import br.edu.ifsudestemg.corrida.model.Percurso;
import br.edu.ifsudestemg.corrida.repository.PercursoRepository;
import br.edu.ifsudestemg.corrida.repository.TapeteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "percurso")
public class PercursoController {

    @Autowired
    private PercursoRepository percursoRepository;

    @GetMapping(value = "")
    public String percursos(Model model){
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista de percursos");
        model.addAttribute("percursos", percursoRepository.findAll());
        return "percurso/percursos";
    }

    @GetMapping(value = "add")
    public String getPercursosAdd(Model model){
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("title", "Adicionar percurso");
        return "percurso/add";
    }

    @PostMapping(value = "add")
    public String postPercursosAdd(Model model, @ModelAttribute Percurso percurso){
        model.addAttribute("title", "Adicionar percurso");
        percursoRepository.save(percurso);
        return "redirect:/percurso";
    }

    @GetMapping(value = "edit/{id}")
    public String getPercursoEdit(Model model, @PathVariable Integer id) {
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar percurso");
        Optional<Percurso> percurso = percursoRepository.findById(id);
        if (percurso.isPresent()){
            model.addAttribute("percurso", percurso.get());
        }
        return "percurso/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String postPercursoEdit(@ModelAttribute Percurso percurso, Model model,
                                 @PathVariable Integer id) throws Exception {
        if (id.equals(percurso.getId())) {
            percursoRepository.save(percurso);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/percurso";
    }

    @GetMapping(value = "delete/{id}")
    public String getPercursoDelete(Model model, @PathVariable Integer id) {
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir percurso");
        Optional<Percurso> percurso = percursoRepository.findById(id);
        if (percurso.isPresent()) {
            model.addAttribute("percurso", percurso.get());
        }

        return "percurso/delete";
    }

    @PostMapping(value = "delete/{id}")
    public String postPercursoDelete(@PathVariable Integer id, @ModelAttribute Percurso percurso) {
        percursoRepository.delete(percurso);
        return "redirect:/percurso";
    }
}