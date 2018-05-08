/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.modelo;

/**
 *
 * @author Nery
 */
public class CartaoCredito{
    
    private String numCartao;
    private String bandeira;
    private String nomeTitular;
    private String cpf;
    private String dataValidade;
    private String codSeguranca;
    private int numParcelas;
    private double valorParcelas;
    private Pagamento pagamento;
    
    private int pagamento_id;

    public CartaoCredito(String numCartao, String bandeira, String nomeTitular, String cpf, String dataValidade, String codSeguranca, int numParcelas, double valorParcelas, Pagamento pagamento) {
        this.numCartao = numCartao;
        this.bandeira = bandeira;
        this.nomeTitular = nomeTitular;
        this.cpf = cpf;
        this.dataValidade = dataValidade;
        this.codSeguranca = codSeguranca;
        this.numParcelas = numParcelas;
        this.valorParcelas = valorParcelas;
        this.pagamento = pagamento;
    }


    public String getNumCartao() {
        return numCartao;
    }

    public void setNumCartao(String numCartao) {
        this.numCartao = numCartao;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getCodSeguranca() {
        return codSeguranca;
    }

    public void setCodSeguranca(String codSeguranca) {
        this.codSeguranca = codSeguranca;
    }

    public int getNumParcelas() {
        return numParcelas;
    }

    public void setNumParcelas(int numParcelas) {
        this.numParcelas = numParcelas;
    }

    public double getValorParcelas() {
        return valorParcelas;
    }

    public void setValorParcelas(double valorParcelas) {
        this.valorParcelas = valorParcelas;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public int getPagamento_id() {
        return pagamento_id;
    }

    public void setPagamento_id(int pagamento_id) {
        this.pagamento_id = pagamento_id;
    }
    
    
}
