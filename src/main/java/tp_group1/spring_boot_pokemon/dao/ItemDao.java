package tp_group1.spring_boot_pokemon.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import tp_group1.spring_boot_pokemon.model.Item;

import java.util.List;


@Repository
public interface ItemDao extends CrudRepository<Item, Long> {
    //trouver tous les objets
    List<Item> findAll();

    //trouver un objet par son nom, une partie de son nom, en ignorant la casse
    List<Item> findByItemNameContainingIgnoringCase(String itemName);

    //trouver tous les objets par nom par ordre croissant
    List<Item> findAllByOrderByItemNameAsc();

    //trouver tous les objets par nom par ordre décroissant
    List<Item> findAllByOrderByItemNameDesc();

    //trouver les objets à partir de l'inventaire id
    List<Item> findByInventoriesId(Long id);

    //trouver un objet par son prix
    List <Item> findByCostBetween(Integer minCost, Integer maxCost);


}
