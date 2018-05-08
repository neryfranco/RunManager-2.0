/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.modelo;

import dao.RankingDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nery
 */
public class Ranking {
    
    private int id;
    private Corrida corrida;
    private Categoria categoria;
    
    private int corrida_id;
    private int categoria_id;

    public Ranking(int id, Corrida corrida, Categoria categoria) {
        this.id = id;
        this.corrida = corrida;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
     
    public Corrida getCorrida() {
        return corrida;
    }

    public void setCorrida(Corrida corrida) {
        this.corrida = corrida;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getCorrida_id() {
        return corrida_id;
    }

    public void setCorrida_id(int corrida_id) {
        this.corrida_id = corrida_id;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }
    
    public static List<Ranking> obterRankings() throws ClassNotFoundException{
        return RankingDAO.obterRankings();
    }
    
    public static Ranking obterRanking(int idRanking) throws ClassNotFoundException {
        return RankingDAO.obterRanking(idRanking);
    }

    public void gravar() throws SQLException, ClassNotFoundException {
        RankingDAO.gravar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        RankingDAO.excluir(this);
    }

    public void alterar() throws SQLException, ClassNotFoundException {
        RankingDAO.alterar(this);
    }
}
