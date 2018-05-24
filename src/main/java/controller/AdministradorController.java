package controller;

import modelo.Administrador;
import repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "administrador")
public class AdministradorController {

    @Autowired
    private AdministradorRepository administradorRepository;

    @GetMapping(value = "")
    public String administradores(Model model){
        model.addAttribute("operacao", "listar");
        model.addAttribute("title", "Lista de administradores");
        model.addAttribute("administradores", administradorRepository.findAll());
        return "administrador/administradores";
    }

    @GetMapping(value = "add")
    public String getAdministradorsAdd(Model model){
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("title", "Adicionar administrador");
        return "administrador/administradores";
    }

    @PostMapping(value = "add")
    public String postAdministradorsAdd(Model model, @ModelAttribute Administrador administrador){
        model.addAttribute("tittle", "Adicionar administrador");
        administradorRepository.save(administrador);
        return "redirect:/administradores";
    }

    @GetMapping(value = "edit/{cpf}")
    public String getAdministradorEdit(Model model, @PathVariable String cpf) {
        model.addAttribute("operacao", "editar");
        model.addAttribute("title", "Editar administrador");
        Optional<Administrador> administrador = administradorRepository.findAdministradorByCpf(cpf);
        if (administrador.isPresent()){
            model.addAttribute("administrador", administrador.get());
        }
        return "administrador/administradores";
    }

    @PostMapping(value = "edit/{id}")
    public String postAdministradorEdit(@ModelAttribute Administrador administrador, Model model,
                                  @PathVariable Long id) throws Exception {
        if (id.equals(administrador.getCpf())) {
            administradorRepository.save(administrador);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/administradores";
    }

    @GetMapping(value = "delete/{cpf}")
    public String getAdministradorDelete(Model model, @PathVariable Long cpf) {
        model.addAttribute("operacao", "deletar");
        model.addAttribute("title", "Excluir administrador");
        Optional<Administrador> administrador = administradorRepository.findAdministradorByCpf(cpf);
        if (administrador.isPresent()) {
            model.addAttribute("administrador", administrador.get());
        }

        return "administrador/administradores";
    }

    @PostMapping(value = "delete/{id}")
    public String postAdministradorDelete(@PathVariable Long id, @ModelAttribute Administrador administrador) {
        administradorRepository.delete(administrador);
        return "redirect:/administradores";
    }

}