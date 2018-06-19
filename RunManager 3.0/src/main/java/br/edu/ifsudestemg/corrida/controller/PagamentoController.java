package br.edu.ifsudestemg.corrida.controller;

import br.edu.ifsudestemg.corrida.model.Pagamento;
import br.edu.ifsudestemg.corrida.repository.IngressoRepository;
import br.edu.ifsudestemg.corrida.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

public class PagamentoController {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private IngressoRepository ingressoRepository;

    @GetMapping(value = "")
    public String pagamentos(Model model){
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista de pagamentos");
        model.addAttribute("pagamentos", pagamentoRepository.findAll());
        return "pagamento/pagamentos";
    }

    @GetMapping(value = "add")
    public String getPagamentosAdd(Model model){
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("title", "Adicionar pagamento");
        model.addAttribute("ingressos", ingressoRepository.findAll());
        return "pagamento/add";
    }

    @PostMapping(value = "add")
    public String postPagamentosAdd(Model model, @ModelAttribute Pagamento pagamento){
        model.addAttribute("tittle", "Adicionar pagamento");
        pagamentoRepository.save(pagamento);
        return "redirect:/pagamento";
    }

    @GetMapping(value = "edit/{id}")
    public String getPagamentosEdit(Model model, @PathVariable Integer id) {
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar pagamento");
        model.addAttribute("ingressos", ingressoRepository.findAll());
        Optional<Pagamento> pagamento = pagamentoRepository.findById(id);
        if (pagamento.isPresent()){
            model.addAttribute("pagamento", pagamento.get());
        }
        return "pagamento/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String postPagamentosEdit(@ModelAttribute Pagamento pagamento, Model model,
                                  @PathVariable Integer id) throws Exception {
        if (id.equals(pagamento.getId())) {
            pagamentoRepository.save(pagamento);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/pagamento";
    }

    @GetMapping(value = "delete/{id}")
    public String getPagamentosDelete(Model model, @PathVariable Integer id) {
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir pagamento");
        model.addAttribute("ingressos", ingressoRepository.findAll());
        Optional<Pagamento> pagamento = pagamentoRepository.findById(id);
        if (pagamento.isPresent()) {
            model.addAttribute("pagamento", pagamento.get());
        }

        return "pagamento/delete";
    }

    @PostMapping(value = "delete/{id}")
    public String postPagamentosDelete(@PathVariable Integer id, @ModelAttribute Pagamento pagamento) {
        pagamentoRepository.delete(pagamento);
        return "redirect:/pagamento";
    }
}
