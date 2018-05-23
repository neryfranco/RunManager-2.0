/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;

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
    private int numParcelas;
    private double valorParcelas;
    @ManyToMany
    private Pagamento pagamento;
    
    private int pagamento_id;

    public CartaoCredito() {}
    
}
