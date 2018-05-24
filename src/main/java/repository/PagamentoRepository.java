package repository;
import com.projeto.modelo.Pagamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface PagamentoRepository extends CrudRepository<Pagamento, Integer> {

}