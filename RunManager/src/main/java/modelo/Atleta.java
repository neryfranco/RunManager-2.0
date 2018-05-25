/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Entity
@Data
public class Atleta extends Usuario implements Serializable {
    @Id
    private String email;
    private String senha;
    private Double pace;
    private String apelido;

    public Atleta(){}
}
