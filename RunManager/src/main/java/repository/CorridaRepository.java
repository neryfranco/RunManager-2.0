package repository;
import modelo.Corrida;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface CorridaRepository extends CrudRepository<Corrida, Integer> {

}