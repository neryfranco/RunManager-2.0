package domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Accessors (chain = true)
public class curso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private Integer cargaHoraria;
    private String tipoCurso;
    private Integer totalPeriodos;
    @ManyToOne
    private professor coordenador;

    public curso(Long id, String nome, Integer cargaHoraria, String tipoCurso, Integer totalPeriodos, professor coordenador) {
        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.tipoCurso = tipoCurso;
        this.totalPeriodos = totalPeriodos;
        this.coordenador = coordenador;
    }
}
