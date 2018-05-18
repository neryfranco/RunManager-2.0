/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.modelo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
@Data
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String cpf;
    private String nome;
    @Column(name = "dataNasc")
    private String dataNascimento;
    private String sexo;
    @Column(name = "tel_cel")
    private String telCel;
    @Column(name = "tel_res")
    private String telRes;
    private String cep;
    private String rua;
    private String uf;
    private String cidade;

    public Usuario(){}


}
