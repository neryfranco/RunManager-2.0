package br.edu.ifsudestemg.corrida.repository;

import br.edu.ifsudestemg.corrida.model.Organizador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface OrganizadorRepository extends CrudRepository<Organizador, String> {}