/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data

public class Ingresso implements Serializable {
    @ManyToOne
    private Lote lote;
    @Id
    private Long numInscricao;
    @ManyToOne
    private Kit kit;
    @ManyToOne
    private Atleta atleta;
    private Integer lote_id;
    private Integer kit_numPeito;
    private String atleta_cpf;
    private Integer pagamento_id;

    public Ingresso() {

    }
}

