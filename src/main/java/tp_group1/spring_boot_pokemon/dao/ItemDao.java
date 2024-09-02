package tp_group1.spring_boot_pokemon.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import tp_group1.spring_boot_pokemon.model.Item;

import java.util.List;


@Repository
public interface ItemDao extends CrudRepository<Item, Long> {

    List<Item> findAll();

    List<Item> findByItemNameContainingIgnoringCase(String itemName);

    List<Item> findAllByOrderByItemNameAsc();

//    List<Item> findAllByOrderByItemNameDesc();

    List<Item> findByInventoriesId(Long id);

    List <Item> findByCostBetween(Integer minCost, Integer maxCost);


}
