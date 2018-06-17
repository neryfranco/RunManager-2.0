package br.edu.ifsudestemg.corrida.repository;

import br.edu.ifsudestemg.corrida.model.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {
}
