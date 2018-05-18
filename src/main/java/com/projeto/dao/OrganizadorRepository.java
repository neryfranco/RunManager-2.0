package com.projeto.dao;
import com.projeto.modelo.Organizador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface OrganizadorRepository extends CrudRepository<Organizador, String> {

}