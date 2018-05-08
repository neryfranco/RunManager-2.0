/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.modelo;

import dao.IngressoDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nery
 */
public class Ingresso {
    private Lote lote;
    private long numInscricao;
    private Kit kit;
    private Atleta atleta;
    
    private int lote_id;
    private int kit_numPeito;
    private String atleta_cpf;
    private int pagamento_id;

    public Ingresso(Lote lote, long numInscricao, Kit kit, Atleta atleta) {
        this.lote = lote;
        this.numInscricao = numInscricao;
        this.kit = kit;
        this.atleta = atleta;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public long getNumInscricao() {
        return numInscricao;
    }

    public void setNumInscricao(long numInscricao) {
        this.numInscricao = numInscricao;
    }

    public Kit getKit() {
        return kit;
    }

    public void setKit(Kit kit) {
        this.kit = kit;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
    }

    public int getLote_id() {
        return lote_id;
    }

    public void setLote_id(int lote_id) {
        this.lote_id = lote_id;
    }

    public int getKit_numPeito() {
        return kit_numPeito;
    }

    public void setKit_numPeito(int kit_numPeito) {
        this.kit_numPeito = kit_numPeito;
    }

    public String getAtleta_cpf() {
        return atleta_cpf;
    }

    public void setAtleta_cpf(String atleta_cpf) {
        this.atleta_cpf = atleta_cpf;
    }

    public int get_id() {
        return pagamento_id;
    }

    public void set_id(int pagamento_id) {
        this.pagamento_id = pagamento_id;
    }
    
    public static List<Ingresso> obterIngressos() throws ClassNotFoundException{
        return IngressoDAO.obterIngressos();
    }
    
    public static Ingresso obterIngresso(int ingresso_id) {
        return null;
    }
    
    public void gravar() throws SQLException, ClassNotFoundException{
        IngressoDAO.gravar(this);
    }
}
