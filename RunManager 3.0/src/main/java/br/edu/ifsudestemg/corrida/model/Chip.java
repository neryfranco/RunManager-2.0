/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsudestemg.corrida.model;


import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;


@Data
@Entity
public class Chip implements Serializable {
    @Id
    private Integer numero;
    private Integer tempoCorrida;
    @ManyToOne
    private Percurso percurso;
    
    private Integer percurso_id;
    private Integer kit_id;

    public Chip() {}


}
