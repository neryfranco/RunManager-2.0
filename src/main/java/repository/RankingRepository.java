package repository;
import com.projeto.modelo.Ranking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface RankingRepository extends CrudRepository<Ranking, Integer> {

}