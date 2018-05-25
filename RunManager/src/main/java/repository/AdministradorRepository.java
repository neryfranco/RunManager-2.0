package repository;
import modelo.Administrador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Component
public interface AdministradorRepository extends CrudRepository<Administrador, String>{
    Optional<Administrador> findAdministradorByCpf(String cpf);
}