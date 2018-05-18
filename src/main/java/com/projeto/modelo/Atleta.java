/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.modelo;


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
public class Atleta extends com.projeto.modelo.Usuario implements Serializable {
    @Id
    private String email;
    private String senha;
    private double pace;
    private String apelido;

    public Atleta(){}
}
