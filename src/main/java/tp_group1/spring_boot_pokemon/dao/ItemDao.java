package tp_group1.spring_boot_pokemon.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import tp_group1.spring_boot_pokemon.model.Item;

@Repository
public interface ItemDao extends CrudRepository<Item, Long> {

//    List<Item> findAll();
//
//    List<Item> findByItemNameContainingIgnoringCase(String itemName);
//
//    List<Item> findAllByOrderByItemNameAsc();
//
//    List<Item> findAllByOrderByItemNameDesc();
}
