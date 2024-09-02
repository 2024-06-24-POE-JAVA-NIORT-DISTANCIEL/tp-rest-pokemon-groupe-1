package tp_group1.spring_boot_pokemon.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import tp_group1.spring_boot_pokemon.model.Inventory;

import java.util.List;

@Repository
public interface InventoryDao extends CrudRepository<Inventory, Long> {
    List<Inventory> findTrainerById(Long trainerId);
}
