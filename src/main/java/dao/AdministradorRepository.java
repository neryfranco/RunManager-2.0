package com.projeto.dao;
import com.projeto.modelo.Administrador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface AdministradorRepository extends CrudRepository<Administrador, String>{

}