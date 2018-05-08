/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.modelo;

import dao.AdministradorDAO;
import dao.AdministradorDAO;
import modelo.Usuario;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nery
 */
public class Administrador extends Usuario {

    private String email;
    private String senha;

    public Administrador
       (String email, 
        String senha, 
        String cpf, 
        String nome, 
        String dataNascimento, 
        String sexo, 
        String telCel, 
        String telRes, 
        String cep, 
        String rua, 
        String uf, 
        String cidade) 
    {
        super(cpf, nome, dataNascimento, sexo, telCel, telRes, cep, rua, uf, cidade);
        this.email = email;
        this.senha = senha;
    }
       
    public Administrador
       (String email, 
        String senha,
        String cpf) 
    {
        super(cpf, null, null, null, null, null, null, null, null, null);
        this.email = email;
        this.senha = senha;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public static List<Administrador> obterAdministradores() throws ClassNotFoundException{
        return AdministradorDAO.obterAdministradores();
    }
    
    public static Administrador obterAdministrador(String cpf) throws ClassNotFoundException{
        return AdministradorDAO.obterAdministrador(cpf);
    }

   public void gravar() throws SQLException, ClassNotFoundException{
        AdministradorDAO.gravar(this);
    }
    
    public void alterar() throws SQLException, ClassNotFoundException{
        AdministradorDAO.alterar(this);
    }
    
    public void excluir() throws SQLException, ClassNotFoundException{
        AdministradorDAO.excluir(this);
    }
}
