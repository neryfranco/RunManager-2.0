package br.edu.ifsudestemg.corrida.controller;

import br.edu.ifsudestemg.corrida.model.Boleto;
import br.edu.ifsudestemg.corrida.repository.BoletoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Controller
@RequestMapping(value = "boleto")

public class BoletoController {

        @Autowired
        private BoletoRepository boletoRepository;

        @GetMapping(value = "")
        public String boletos(Model model){
            model.addAttribute("operacao", "listar");
            model.addAttribute("title", "Lista de boletos");
            model.addAttribute("boletos", boletoRepository.findAll());
            return "boleto/boletos";
        }

        @GetMapping(value = "add")
        public String getBoletosAdd(Model model){
            model.addAttribute("operacao", "adicionar");
            model.addAttribute("title", "Adicionar boleto");
            return "boleto/add";
        }

        @PostMapping(value = "add")
        public String postBoletosAdd(Model model, @ModelAttribute Boleto boleto){
            model.addAttribute("tittle", "Adicionar boleto");
            boletoRepository.save(boleto);
            return "redirect:/boletos";
        }

        @GetMapping(value = "edit/{id}")
        public String getBoletosEdit(Model model, @PathVariable Integer id) {
            model.addAttribute("operacao", "editar");
            model.addAttribute("title", "Editar boleto");
            Optional<Boleto> boleto = boletoRepository.findById(id);
            if (boleto.isPresent()){
                model.addAttribute("boleto", boleto.get());
            }
            return "boleto/edit";
        }

        @PostMapping(value = "edit/{id}")
        public String postBoletosEdit(@ModelAttribute Boleto boleto, Model model,
                                     @PathVariable Integer id) throws Exception {
            if (id.equals(boleto.getId())) {
                boletoRepository.save(boleto);
            } else {
                model.addAttribute("error", "Dados incorretos");
            }
            return "redirect:/boletos";
        }

        @GetMapping(value = "delete/{id}")
        public String getBoletosDelete(Model model, @PathVariable Integer id) {
            model.addAttribute("operacao", "deletar");
            model.addAttribute("title", "Excluir boleto");
            Optional<Boleto> boleto = boletoRepository.findById(id);
            if (boleto.isPresent()) {
                model.addAttribute("boleto", boleto.get());
            }

            return "boleto/delete";
        }

        @PostMapping(value = "delete/{id}")
        public String postBoletosDelete(@PathVariable Integer id, @ModelAttribute Boleto boleto) {
            boletoRepository.delete(boleto);
            return "redirect:/boletos";
        }
}

