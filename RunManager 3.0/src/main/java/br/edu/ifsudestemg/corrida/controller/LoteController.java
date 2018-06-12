package br.edu.ifsudestemg.corrida.controller;


import br.edu.ifsudestemg.corrida.model.Corrida;
import br.edu.ifsudestemg.corrida.model.Lote;
import br.edu.ifsudestemg.corrida.repository.CorridaRepository;
import br.edu.ifsudestemg.corrida.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "lote")
public class LoteController {

    @Autowired
    private LoteRepository loteRepository;
    private CorridaRepository corridaRepository;

    @GetMapping(value = "")
    public String lotes(Model model){
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista de lotes");
        model.addAttribute("lotes", loteRepository.findAll());
        return "lote/lotes";
    }

    @GetMapping(value = "add")
    public String getLotesAdd(Model model){
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("title", "Adicionar lote");
        model.addAttribute("corridas", corridaRepository.findAll());
        return "lote/add";
    }

    @PostMapping(value = "add")
    public String postLotesAdd(Model model, @ModelAttribute Lote lote){
        model.addAttribute("title", "Adicionar lote");
        loteRepository.save(lote);
        return "redirect:/lote";
    }

    @GetMapping(value = "edit/{corrida}/{id}")
    public String getLoteEdit(Model model, @PathVariable Integer id) {
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar lote");
        model.addAttribute("corridas", corridaRepository.findAll());
        Optional<Lote> lote = loteRepository.findById(id);
        if (lote.isPresent()){
            model.addAttribute("lote", lote.get());
        }
        return "lote/edit";
    }

    @PostMapping(value = "edit/{corrida}/{id}")
    public String postLoteEdit(@ModelAttribute Lote lote, Model model,
                                 @PathVariable Integer id, @PathVariable Corrida corrida) throws Exception {
        if (id.equals(lote.getId())) {
            loteRepository.save(lote);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/lote";
    }

    @GetMapping(value = "delete/{corrida}/{id}")
    public String getLoteDelete(Model model, @PathVariable Integer id) {
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir lote");
        Optional<Lote> lote = loteRepository.findById(id);
        if (lote.isPresent()) {
            model.addAttribute("lote", lote.get());
        }

        return "lote/delete";
    }

    @PostMapping(value = "delete/{corrida}/{id}")
    public String postLoteDelete(@PathVariable Integer id, @ModelAttribute Lote lote) {
        loteRepository.delete(lote);
        return "redirect:/lote";
    }
}