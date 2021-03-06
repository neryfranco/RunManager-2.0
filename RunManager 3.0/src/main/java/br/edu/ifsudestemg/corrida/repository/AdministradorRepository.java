package br.edu.ifsudestemg.corrida.repository;

import br.edu.ifsudestemg.corrida.model.Administrador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface AdministradorRepository extends CrudRepository<Administrador, String> {
    boolean existsAdministradorByEmailAndSenha(String email,  String senha);
    Administrador getAdministradorByEmail(String email);

}