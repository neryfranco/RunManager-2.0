/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsudestemg.corrida.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Corrida implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    @ManyToOne
    private Percurso percurso;
    private String localLargada;
    private String localChegada;
    private String horarioLargada;
    private String dataCorrida;
    private String dataRetiradaKit;
    private String localRetiradaKit;
    private Integer duracaoLimite;
    private Integer numMaxParticipantes;
}
