package br.edu.ifsudestemg.corrida.repository;

import br.edu.ifsudestemg.corrida.model.Atleta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface AtletaRepository extends CrudRepository<Atleta, String> {
    boolean existsAtletaByEmailAndSenha(String email,  String senha);
    Atleta getAtletaByEmail(String email);

}