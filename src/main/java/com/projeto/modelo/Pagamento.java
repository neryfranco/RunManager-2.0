/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.modelo;

import dao.PagamentoDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nery
 */
public class Pagamento {
    
    private int id;
    private String metodoPagamento; 
    private double valor;
    private Ingresso ingresso;
    
    private int ingresso_id;

    public Pagamento(int id, String metodoPagamento, double valor, Ingresso ingresso) {
        this.id = id;
        this.metodoPagamento = metodoPagamento;
        this.valor = valor;
        this.ingresso = ingresso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Ingresso getIngresso() {
        return ingresso;
    }

    public void setIngresso(Ingresso ingresso) {
        this.ingresso = ingresso;
    }

    public int getIngresso_id() {
        return ingresso_id;
    }

    public void setIngresso_id(int ingresso_id) {
        this.ingresso_id = ingresso_id;
    }
    
    public static List<Pagamento> obterPagamentos() throws ClassNotFoundException{
        return PagamentoDAO.obterPagamentos();
    }
    
    public static Pagamento obterPagamento(int id) throws ClassNotFoundException {
        return PagamentoDAO.obterPagamento(id);
    }

    public void gravar() throws SQLException, ClassNotFoundException {
        PagamentoDAO.gravar(this);
    }
    
    public void alterar() throws SQLException, ClassNotFoundException{
        PagamentoDAO.alterar(this);
    }
    
    public void excluir() throws SQLException, ClassNotFoundException{
        PagamentoDAO.excluir(this);
    }
}
