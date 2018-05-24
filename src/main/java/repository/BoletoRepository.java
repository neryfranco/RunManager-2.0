package repository;

import com.projeto.modelo.Boleto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface BoletoRepository extends CrudRepository<Boleto, String> {

}