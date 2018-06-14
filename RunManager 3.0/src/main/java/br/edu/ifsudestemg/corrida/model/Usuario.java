/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsudestemg.corrida.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;


@MappedSuperclass
@Data
@Accessors(chain = true)
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String cpf;
    private String nome;
    private String data_nascimento;
    private String sexo;
    private String telCel;
    private String telRes;
    private String cep;
    private String rua;
    private String uf;
    private String cidade;

    public Usuario(){}


}
