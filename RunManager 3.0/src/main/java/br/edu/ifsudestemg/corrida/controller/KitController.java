package br.edu.ifsudestemg.corrida.controller;


import br.edu.ifsudestemg.corrida.model.Kit;
import br.edu.ifsudestemg.corrida.repository.KitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping(value = "kit")
public class KitController {

    @Autowired
    private KitRepository kitRepository;

    @GetMapping(value = "")
    public String kits(Model model){
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista de kits");
        model.addAttribute("kits", kitRepository.findAll());
        return "kit/kits";
    }

    @GetMapping(value = "add")
    public String getKitsAdd(Model model){
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("title", "Adicionar kit");
        return "kit/add";
    }

    @PostMapping(value = "add")
    public String postKitsAdd(Model model, @ModelAttribute Kit kit){
        model.addAttribute("title", "Adicionar kit");
        kitRepository.save(kit);
        return "redirect:/kit";
    }

    @GetMapping(value = "edit/{numPeito}")
    public String getKitEdit(Model model, @PathVariable Integer numPeito) {
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar kit");
        Optional<Kit> kit = kitRepository.findById(numPeito);
        if (kit.isPresent()){
            model.addAttribute("kit", kit.get());
        }
        return "kit/edit";
    }

    @PostMapping(value = "edit/{numPeito}")
    public String postKitEdit(@ModelAttribute Kit kit, Model model,
                                 @PathVariable Integer numPeito) throws Exception {
        if (numPeito.equals(kit.getNumPeito())) {
            kitRepository.save(kit);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/kit";
    }

    @GetMapping(value = "delete/{numPeito}")
    public String getKitDelete(Model model, @PathVariable Integer numPeito) {
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir kit");
        Optional<Kit> kit = kitRepository.findById(numPeito);
        if (kit.isPresent()) {
            model.addAttribute("kit", kit.get());
        }

        return "kit/delete";
    }

    @PostMapping(value = "delete/{numPeito}")
    public String postKitDelete(@PathVariable Integer numPeito, @ModelAttribute Kit kit) {
        kitRepository.delete(kit);
        return "redirect:/kit";
    }
}