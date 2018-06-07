/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsudestemg.corrida.model;


import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.id.Assigned;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;


@Accessors(chain = true)
@Entity
@Data
public class Administrador extends Usuario implements Serializable {
   // @Id
    private String email;
    private String senha;

  // public Administrador(){}

}
