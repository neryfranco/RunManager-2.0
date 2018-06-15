package br.edu.ifsudestemg.corrida.controller;


import br.edu.ifsudestemg.corrida.model.Atleta;
import br.edu.ifsudestemg.corrida.model.Ingresso;
import br.edu.ifsudestemg.corrida.model.Kit;
import br.edu.ifsudestemg.corrida.repository.AtletaRepository;
import br.edu.ifsudestemg.corrida.repository.IngressoRepository;
import br.edu.ifsudestemg.corrida.repository.KitRepository;
import br.edu.ifsudestemg.corrida.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "ingresso")
public class IngressoController {

    @Autowired
    private IngressoRepository ingressoRepository;
    @Autowired
    private LoteRepository loteRepository;
    @Autowired
    private KitRepository kitRepository;
    @Autowired
    private AtletaRepository atletaRepository;

    @GetMapping(value = "")
    public String ingressos(Model model){
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista de ingressos");
        model.addAttribute("ingressos", ingressoRepository.findAll());
        return "ingresso/ingressos";
    }

    @GetMapping(value = "add")
    public String getIngressosAdd(Model model){
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("title", "Adicionar ingresso");
        model.addAttribute("lotes", loteRepository.findAll());
        model.addAttribute("kits", kitRepository.findAll());
        model.addAttribute("atletas", atletaRepository.findAll());
        return "ingresso/add";
    }

    @PostMapping(value = "add")
    public String postIngressosAdd(Model model, @ModelAttribute Ingresso ingresso){
        model.addAttribute("title", "Adicionar ingresso");
        ingressoRepository.save(ingresso);
        return "redirect:/ingresso";
    }

    @GetMapping(value = "edit/{numInscricao}")
    public String getIngressoEdit(Model model, @PathVariable Integer numInscricao) {
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar ingresso");
        model.addAttribute("lotes", loteRepository.findAll());
        model.addAttribute("kits", kitRepository.findAll());
        model.addAttribute("atletas", atletaRepository.findAll());
        Optional<Ingresso> ingresso = ingressoRepository.findById(numInscricao);
        if (ingresso.isPresent()){
            model.addAttribute("ingresso", ingresso.get());
        }
        return "ingresso/edit";
    }

    @PostMapping(value = "edit/{numInscricao}")
    public String postIngressoEdit(@ModelAttribute Ingresso ingresso, Model model,
                                  @PathVariable Integer numInscricao) throws Exception {
        if (numInscricao.equals(ingresso.getNumInscricao())) {
            ingressoRepository.save(ingresso);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/ingresso";
    }

    @GetMapping(value = "delete/{numInscricao}")
    public String getIngressoDelete(Model model, @PathVariable Integer numInscricao) {
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir ingresso");
        model.addAttribute("lotes", loteRepository.findAll());
        model.addAttribute("kits", kitRepository.findAll());
        model.addAttribute("atletas", atletaRepository.findAll());
        Optional<Ingresso> ingresso = ingressoRepository.findById(numInscricao);
        if (ingresso.isPresent()) {
            model.addAttribute("ingresso", ingresso.get());
        }

        return "ingresso/delete";
    }

    @PostMapping(value = "delete/{numInscricao}")
    public String postIngressoDelete(@PathVariable Integer numInscricao, @ModelAttribute Ingresso ingresso) {
        ingressoRepository.delete(ingresso);
        return "redirect:/ingresso";
    }
}