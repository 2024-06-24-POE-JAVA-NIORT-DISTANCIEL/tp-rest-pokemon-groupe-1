package tp_group1.spring_boot_pokemon.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import tp_group1.spring_boot_pokemon.model.Inventory;

import java.util.List;

@Repository
public interface InventoryDao extends CrudRepository<Inventory, Long> {
    //trouver un inventaire par le dresseur id
    List<Inventory> findByTrainerId(Long id);

    //trouver tous les inventaires
    List<Inventory> findAll();


//    // supprimer un inventaire par id
//    <List>Inventory deleteByInventoryId(Long id);


}