/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.modelo;

import dao.CategoriaDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nery
 */
public class Categoria {

         
    private int id;
    private String sexo;
    private String idadeIni;
    private String idadeFim;
    private String descricao;

    public Categoria(int id, String sexo, String idadeIni, String idadeFim, String descricao) {
        this.id = id;
        this.sexo = sexo;
        this.idadeIni = idadeIni;
        this.idadeFim = idadeFim;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getIdadeIni() {
        return idadeIni;
    }

    public void setIdadeIni(String idadeIni) {
        this.idadeIni = idadeIni;
    }

    public String getIdadeFim() {
        return idadeFim;
    }

    public void setIdadeFim(String idadeFim) {
        this.idadeFim = idadeFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public static List obterCategorias() throws ClassNotFoundException{
        return CategoriaDAO.obterCategorias();
    }
    
    public static Categoria obterCategoria(int categoria_id) throws ClassNotFoundException {
        return CategoriaDAO.obterCategoria(categoria_id);
    }

    public void gravar() throws SQLException, ClassNotFoundException{
        CategoriaDAO.gravar(this);
    }

    public void alterar() throws SQLException, ClassNotFoundException{
        CategoriaDAO.alterar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException{
        CategoriaDAO.excluir(this);
    }
}
