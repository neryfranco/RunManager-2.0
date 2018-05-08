/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.modelo;

import dao.TapeteDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nery
 */
public class Tapete {
    
    private int id;
    private String cep;
    private String rua;
    private String cidade;
    private String uf;
    private String referencia;

    public Tapete(int id, String cep, String rua, String cidade, String uf, String referencia) {
        this.id = id;
        this.cep = cep;
        this.rua = rua;
        this.cidade = cidade;
        this.uf = uf;
        this.referencia = referencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    
    public static List obterTapetes() throws ClassNotFoundException {
        return TapeteDAO.obterTapetes();
    }
    
    public static Tapete obterTapete(int id) throws ClassNotFoundException {
        return TapeteDAO.obterTapete(id);
    }

    public void gravar() throws SQLException, ClassNotFoundException {
        TapeteDAO.gravar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        TapeteDAO.excluir(this);
    }

    public void editar() throws SQLException, ClassNotFoundException {
        TapeteDAO.alterar(this);
    }
}
