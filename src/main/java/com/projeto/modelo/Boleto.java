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
public class Boleto {
    
    private String nome;
    private String cpf;
    private Pagamento pagamento;
    
    private int pagamento_id;

    public Boleto(String nome, String cpf, Pagamento pagamento) {
        this.nome = nome;
        this.cpf = cpf;
        this.pagamento = pagamento;
    }

    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
