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
public class Ranking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToMany
    private Corrida corrida;
    @ManyToOne
    private Categoria categoria;
    
    private Integer corrida_id;
    private Integer categoria_id;

    public Ranking() {}


}
