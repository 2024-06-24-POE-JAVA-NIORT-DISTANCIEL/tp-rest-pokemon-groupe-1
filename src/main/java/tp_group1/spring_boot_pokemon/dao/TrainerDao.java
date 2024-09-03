package tp_group1.spring_boot_pokemon.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tp_group1.spring_boot_pokemon.model.Trainer;

import java.util.List;

@Repository
public interface TrainerDao extends CrudRepository<Trainer, Long> {
    //trouver tous les dresseurs
    List<Trainer> findAll();

    //trouver un dresseur par son nom, une partie de son, sans prendre en compte la casse
    List<Trainer> findByUsernameContainingIgnoringCase(String username);

    //trouver tous les dresseurs leur nom par ordre alphabétique
    List<Trainer> findAllByOrderByUsernameAsc();

    //trouver un dresseur par le pokemon id
    List<Trainer> findByPokemonsId(Long id);
}
