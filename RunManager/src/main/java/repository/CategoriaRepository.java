package repository;
import modelo.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {

}