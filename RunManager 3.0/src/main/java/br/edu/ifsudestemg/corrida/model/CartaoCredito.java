/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsudestemg.corrida.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class CartaoCredito implements Serializable {
    @Id
    private String numCartao;
    private String bandeira;
    private String nomeTitular;
    private String cpf;
    private String dataValidade;
    private String codSeguranca;
    private Integer numParcelas;
    private Double valorParcelas;
    @ManyToOne
    private Pagamento pagamento;

}
