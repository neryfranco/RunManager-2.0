package repository;
import com.projeto.modelo.Lote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface LoteRepository extends CrudRepository<Lote, Integer> {

}