package com.projeto.dao;
import com.projeto.modelo.Ingresso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface IngressoRepository extends CrudRepository<Ingresso, Long> {

}