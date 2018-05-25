package repository;
import modelo.CartaoCredito;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface CartaoCreditoRepository extends CrudRepository<CartaoCredito, Integer> {

}