package com.projeto.dao;
import com.projeto.modelo.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface UsuarioRepository extends CrudRepository<Usuario, String> {

}