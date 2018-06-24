package br.edu.ifsudestemg.corrida.controller;

import br.edu.ifsudestemg.corrida.model.Administrador;
import br.edu.ifsudestemg.corrida.model.Atleta;
import br.edu.ifsudestemg.corrida.model.Organizador;
import br.edu.ifsudestemg.corrida.repository.AdministradorRepository;
import br.edu.ifsudestemg.corrida.repository.AtletaRepository;
import br.edu.ifsudestemg.corrida.repository.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(value = "")
public class LoginController {
    @Autowired
    private AdministradorRepository administradorRepository;
    @Autowired
    private AtletaRepository atletaRepository;
    @Autowired
    private OrganizadorRepository organizadorRepository;


    @GetMapping
    public String get(Model model, HttpSession session) {
        model.addAttribute("msg", "");
        if(session.getAttribute("tipoLogin") != null){
            return "redirect:/painel";
        }
        return "index";

    }


    @PostMapping
    public String postAdd(Model model, HttpSession session, @RequestParam String email, @RequestParam String senha, @RequestParam String tipoUsuario) {

        if (tipoUsuario.equals("administrador")) {
            if (administradorRepository.existsAdministradorByEmailAndSenha(email, senha)) {
                Administrador administrador = administradorRepository.getAdministradorByEmail(email);
                session.setAttribute("tipoLogin", tipoUsuario);
                session.setAttribute("id", administrador.getEmail());
                return "redirect:/painel";
            }
        }
        if (tipoUsuario.equals("atleta")) {
            if (atletaRepository.existsAtletaByEmailAndSenha(email, senha)) {
                Atleta atleta = atletaRepository.getAtletaByEmail(email);
                session.setAttribute("tipoLogin", tipoUsuario);
                session.setAttribute("id", atleta.getEmail());
                return "redirect:/painel";
            }
        }
        if (tipoUsuario.equals("organizador")) {
            if (organizadorRepository.existsOrganizadorByEmailAndSenha(email, senha)) {
                Organizador organizador = organizadorRepository.getOrganizadorByEmail(email);
                session.setAttribute("tipoLogin", tipoUsuario);
                session.setAttribute("id", organizador.getEmail());
                return "redirect:/painel";
            }
        }
        model.addAttribute("msg", "Erro inesperado!");
        return "index";
    }

    @GetMapping(value = "/logout")
    public String getLogout(Model model, HttpSession session) {
        model.addAttribute("msg", "");
        session.invalidate();
        return "redirect:/";
    }

}