package br.edu.ifsudestemg.corrida.controller;

import br.edu.ifsudestemg.corrida.model.CartaoCredito;
import br.edu.ifsudestemg.corrida.repository.CartaoCreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Controller
@RequestMapping(value = "cartaoCredito")

public class CartaoCreditoController {
    @Autowired
    private CartaoCreditoRepository cartaoCreditoRepository;

    @GetMapping(value = "")
    public String cartaoCreditos(Model model){
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista de cartaoCreditos");
        model.addAttribute("cartaoCreditos", cartaoCreditoRepository.findAll());
        return "cartaoCredito/cartaoCreditos";
    }

    @GetMapping(value = "add")
    public String getCartaoCreditossAdd(Model model){
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("title", "Adicionar cartaoCredito");
        return "cartaoCredito/add";
    }

    @PostMapping(value = "add")
    public String postCartaoCreditossAdd(Model model, @ModelAttribute CartaoCredito cartaoCredito){
        model.addAttribute("tittle", "Adicionar cartaoCredito");
        cartaoCreditoRepository.save(cartaoCredito);
        return "redirect:/cartaoCreditos";
    }

    @GetMapping(value = "edit/{numCartao}")
    public String getCartaoCreditosEdit(Model model, @PathVariable String numCartao) {
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar cartaoCredito");
        Optional<CartaoCredito> cartaoCredito = cartaoCreditoRepository.findById(numCartao);
        if (cartaoCredito.isPresent()){
            model.addAttribute("cartaoCredito", cartaoCredito.get());
        }
        return "cartaoCredito/edit";
    }

    @PostMapping(value = "edit/{numCartao}")
    public String postCartaoCreditosEdit(@ModelAttribute CartaoCredito cartaoCredito, Model model,
                                  @PathVariable String numCartao) throws Exception {
        if (numCartao.equals(cartaoCredito.getNumCartao())) {
            cartaoCreditoRepository.save(cartaoCredito);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/cartaoCreditos";
    }

    @GetMapping(value = "delete/{numCartao}")
    public String getCartaoCreditosDelete(Model model, @PathVariable String numCartao) {
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir cartaoCredito");
        Optional<CartaoCredito> cartaoCredito = cartaoCreditoRepository.findById(numCartao);
        if (cartaoCredito.isPresent()) {
            model.addAttribute("cartaoCredito", cartaoCredito.get());
        }

        return "cartaoCredito/delete";
    }

    @PostMapping(value = "delete/{numCartao}")
    public String postCartaoCreditosDelete(@PathVariable String numCartao, @ModelAttribute CartaoCredito cartaoCredito) {
        cartaoCreditoRepository.delete(cartaoCredito);
        return "redirect:/cartaoCreditos";
    }
}
