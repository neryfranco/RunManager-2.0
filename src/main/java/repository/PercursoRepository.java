package repository;
import com.projeto.modelo.Percurso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface PercursoRepository extends CrudRepository<Percurso, Integer> {

}