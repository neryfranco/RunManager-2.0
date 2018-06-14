/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsudestemg.corrida.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Lote implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private Corrida corrida;
    private Double preco;
    private String dataLimite;
    private Integer numIngressos;
    @OneToMany(mappedBy = "lote")
    private List<Ingresso> ingressos;

    public void setNumIngressos(){
        ingressos = new ArrayList<>(numIngressos);
    }
}
    