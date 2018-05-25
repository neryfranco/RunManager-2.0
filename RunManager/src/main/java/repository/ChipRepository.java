package repository;
import modelo.Chip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface ChipRepository extends CrudRepository<Chip, Integer> {

}