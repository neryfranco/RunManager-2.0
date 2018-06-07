/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsudestemg.corrida.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class Corrida implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    private Percurso percurso;
    private String localLargada;
    private String localChegada;
    private String horaLargada;
    private String dataCorrida;
    private String dataRetiradaKit;
    private String localRetiradaKit;
    private Integer duracaoLimite;
    private Integer numMaxParticipantes;
    
    private Integer percurso_id;

    public Corrida(){}
    

}
