package br.edu.ifsudestemg.corrida.repository;

import br.edu.ifsudestemg.corrida.model.Ingresso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface IngressoRepository extends CrudRepository<Ingresso, Integer> {}