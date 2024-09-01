package tp_group1.spring_boot_pokemon.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tp_group1.spring_boot_pokemon.model.Fight;

import java.util.List;

@Repository
public interface FightDao extends CrudRepository<Fight, Long> {

    List<Fight> findAll();
}
