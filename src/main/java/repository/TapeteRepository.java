package repository;
import com.projeto.modelo.Tapete;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface TapeteRepository extends CrudRepository<Tapete, Integer> {

}