    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    package com.projeto.modelo;

    import lombok.Data;

    import javax.persistence.*;
    import java.io.Serializable;


    @Entity
    @Data
public class Boleto implements Serializable {

    private String nome;
    @Id
    private String cpf;
    @ManyToMany
    private Pagamento pagamento;

    private int pagamento_id;

    public Boleto() {}

    


    
}
