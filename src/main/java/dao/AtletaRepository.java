package com.projeto.dao;
import com.projeto.modelo.Atleta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface AtletaRepository extends CrudRepository<Atleta, String> {

}