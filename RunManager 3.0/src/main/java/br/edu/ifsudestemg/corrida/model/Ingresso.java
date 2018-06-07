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

public class Ingresso implements Serializable {
    @Id
    private Long numInscricao;
    @ManyToOne
    private Kit kit;
    @ManyToOne
    private Atleta atleta;
    @ManyToOne
    private Lote lote;

}

