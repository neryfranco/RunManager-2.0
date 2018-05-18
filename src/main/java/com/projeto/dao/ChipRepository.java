package com.projeto.dao;
import com.projeto.modelo.Chip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface ChipRepository extends CrudRepository<Chip, Integer> {

}