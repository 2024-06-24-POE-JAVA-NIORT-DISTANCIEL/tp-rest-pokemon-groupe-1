package tp_group1.spring_boot_pokemon.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import tp_group1.spring_boot_pokemon.model.Attack;

import java.util.List;

@Repository
public interface AttackDao extends CrudRepository<Attack, Long> {
    //trouver toutes les attaques
    List<Attack> findAll();

    //trouver une attaque par son nom, une partie de son nom, en ignorant la casse
    List<Attack> findByAttackNameContainingIgnoringCase(String attackName);

    //trouver toutes les attaques par nom par ordre croissant
    List<Attack> findAllByOrderByAttackNameAsc();

    //trouver une attaque par l'espèce id
    List<Attack> findBySpeciesId(Long id);

    //trouver une attaque par le pokemon id
    List<Attack> findByPokemonsId(Long id);
}
