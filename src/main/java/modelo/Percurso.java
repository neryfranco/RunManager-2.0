/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;


@Data
@Entity
public class Percurso implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String itinerario;
    private Integer distancia;
    private List<Tapete> tapetes;
    
    private Integer categoria_id;
    private List<Integer> tapetes_id;

    public Percurso() {}
    
}
