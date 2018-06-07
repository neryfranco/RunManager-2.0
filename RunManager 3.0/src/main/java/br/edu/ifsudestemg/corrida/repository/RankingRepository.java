package br.edu.ifsudestemg.corrida.repository;

import br.edu.ifsudestemg.corrida.model.Ranking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface RankingRepository extends CrudRepository<Ranking, Integer> {}