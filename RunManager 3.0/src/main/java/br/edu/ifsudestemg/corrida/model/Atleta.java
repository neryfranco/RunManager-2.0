/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsudestemg.corrida.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Accessors(chain = true)
@Entity
@Data
public class Atleta extends Usuario implements Serializable {
    private String email;
    private String senha;
    private Double pace;
    private String apelido;
    private String camisa;

}
