/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.modelo;

import dao.CorridaDAO;
import lombok.Data;
import modelo.Percurso;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nery
 */
@Entity
@Data
public class Corrida {
    @Id
    private int id;
    private String nome;
    private Percurso percurso;
    private String localLargada;
    private String localChegada;
    private String horaLargada;
    private String dataCorrida;
    private String dataRetiradaKit;
    private String localRetiradaKit;
    private int duracaoLimite;
    private int numMaxParticipantes;
    
    private int percurso_id;

    public Corrida(int id, String nome, Percurso percurso, String localLargada, String localChegada, String horaLargada, String dataCorrida, String dataRetiradaKit, String localRetiradaKit, int duracaoLimite, int numMaxParticipantes) {
        this.id = id;
        this.nome = nome;
        this.percurso = percurso;
        this.localLargada = localLargada;
        this.localChegada = localChegada;
        this.horaLargada = horaLargada;
        this.dataCorrida = dataCorrida;
        this.dataRetiradaKit = dataRetiradaKit;
        this.localRetiradaKit = localRetiradaKit;
        this.duracaoLimite = duracaoLimite;
        this.numMaxParticipantes = numMaxParticipantes;
    }
    
    public static List obterCorridas() throws ClassNotFoundException{
        return CorridaDAO.obterCorridas();
    }
    
    public static Corrida obterCorrida(int id) throws ClassNotFoundException, SQLException{
        return CorridaDAO.obterCorrida(id);
    }

    public void gravar() throws SQLException, ClassNotFoundException{
        CorridaDAO.gravar(this);
    }
    
    public void alterar() throws SQLException, ClassNotFoundException{
        CorridaDAO.alterar(this);
    }
    
    public void excluir() throws SQLException, ClassNotFoundException{
        CorridaDAO.excluir(this);
    }
}
