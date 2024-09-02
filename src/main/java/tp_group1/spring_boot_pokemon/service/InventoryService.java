package tp_group1.spring_boot_pokemon.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp_group1.spring_boot_pokemon.dao.InventoryDao;
import tp_group1.spring_boot_pokemon.model.Inventory;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryDao inventoryDao;


    // Récupération de l'inventaire du trainer
    public List<Inventory> findInventoryByTrainer(Long trainerId) {
        return inventoryDao.findTrainerById(trainerId);
    }


    // Ajout d'objet à l'inventaire
    public Inventory addInventory(Inventory inventory) {
        return inventoryDao.save(inventory);




    }
}
