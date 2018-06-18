package br.edu.ifsudestemg.corrida.controller;

import br.edu.ifsudestemg.corrida.model.Categoria;
import br.edu.ifsudestemg.corrida.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Controller
@RequestMapping(value = "categoria")

public class CategoriaController {

        @Autowired
        private CategoriaRepository categoriaRepository;

        @GetMapping(value = "")
        public String categorias(Model model){
            model.addAttribute("operacao", "listar");
            model.addAttribute("title", "Lista de categorias");
            model.addAttribute("categorias", categoriaRepository.findAll());
            return "categoria/categorias";
        }

        @GetMapping(value = "add")
        public String getCategoriasAdd(Model model){
            model.addAttribute("operacao", "adicionar");
            model.addAttribute("title", "Adicionar categoria");
            return "categoria/add";
        }

        @PostMapping(value = "add")
        public String postCategoriasAdd(Model model, @ModelAttribute Categoria categoria){
            model.addAttribute("tittle", "Adicionar categoria");
            categoriaRepository.save(categoria);
            return "redirect:/categoria";
        }

        @GetMapping(value = "edit/{id}")
        public String getCategoriaEdit(Model model, @PathVariable Integer id) {
            model.addAttribute("operacao", "editar");
            model.addAttribute("title", "Editar categoria");
            Optional<Categoria> categoria = categoriaRepository.findById(id);
            if (categoria.isPresent()){
                model.addAttribute("categoria", categoria.get());
            }
            return "categoria/edit";
        }

        @PostMapping(value = "edit/{id}")
        public String postCategoriaEdit(@ModelAttribute Categoria categoria, Model model,
                                      @PathVariable Integer id) throws Exception {
            if (id.equals(categoria.getId())) {
                categoriaRepository.save(categoria);
            } else {
                model.addAttribute("error", "Dados incorretos");
            }
            return "redirect:/categoria";
        }

        @GetMapping(value = "delete/{id}")
        public String getCategoriaDelete(Model model, @PathVariable Integer id) {
            model.addAttribute("operacao", "deletar");
            model.addAttribute("title", "Excluir categoria");
            Optional<Categoria> categoria = categoriaRepository.findById(id);
            if (categoria.isPresent()) {
                model.addAttribute("categoria", categoria.get());
            }

            return "categoria/delete";
        }

        @PostMapping(value = "delete/{id}")
        public String postCategoriaDelete(@PathVariable Integer id, @ModelAttribute Categoria categoria) {
            categoriaRepository.delete(categoria);
            return "redirect:/categoria";
        }

    }

