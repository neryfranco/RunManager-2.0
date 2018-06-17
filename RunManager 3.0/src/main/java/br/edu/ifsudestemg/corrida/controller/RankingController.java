package br.edu.ifsudestemg.corrida.controller;

import br.edu.ifsudestemg.corrida.model.Categoria;
import br.edu.ifsudestemg.corrida.model.Ranking;
import br.edu.ifsudestemg.corrida.repository.CategoriaRepository;
import br.edu.ifsudestemg.corrida.repository.CorridaRepository;
import br.edu.ifsudestemg.corrida.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "ranking")
public class RankingController {

    @Autowired
    private RankingRepository rankingRepository;

    @Autowired
    private CorridaRepository corridaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping(value = "")
    public String rankings(Model model){
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista de rankings");
        model.addAttribute("rankings", rankingRepository.findAll());
        return "ranking/rankings";
    }

    @GetMapping(value = "add")
    public String getRankingesAdd(Model model){
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("title", "Adicionar ranking");
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("corridas", corridaRepository.findAll());
        return "ranking/add";
    }

    @PostMapping(value = "add")
    public String postRankingesAdd(Model model, @ModelAttribute Ranking ranking){
        model.addAttribute("title", "Adicionar ranking");
        rankingRepository.save(ranking);
        return "redirect:/ranking";
    }

    @GetMapping(value = "edit/{id}")
    public String getRankingEdit(Model model, @PathVariable Integer id) {
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar ranking");
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("corridas", corridaRepository.findAll());
        Optional<Ranking> ranking = rankingRepository.findById(id);
        if (ranking.isPresent()){
            model.addAttribute("ranking", ranking.get());
        }
        return "ranking/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String postRankingEdit(@ModelAttribute Ranking ranking, Model model,
                                        @PathVariable Integer id) throws Exception {
        if (id.equals(ranking.getId())) {
            rankingRepository.save(ranking);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/ranking";
    }

    @GetMapping(value = "delete/{id}")
    public String getRankingDelete(Model model, @PathVariable Integer id) {
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir ranking");
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("corridas", corridaRepository.findAll());
        Optional<Ranking> ranking = rankingRepository.findById(id);
        if (ranking.isPresent()) {
            model.addAttribute("ranking", ranking.get());
        }

        return "ranking/delete";
    }

    @PostMapping(value = "delete/{id}")
    public String postRankingDelete(@PathVariable Integer id, @ModelAttribute Ranking ranking) {
        rankingRepository.delete(ranking);
        return "redirect:/ranking";
    }

}