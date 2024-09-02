package tp_group1.spring_boot_pokemon.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp_group1.spring_boot_pokemon.dao.InventoryDao;
import tp_group1.spring_boot_pokemon.model.Inventory;
import tp_group1.spring_boot_pokemon.model.Item;

import java.util.List;
import java.util.Optional;

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

    // Récupération de tout l'inventaire
    public List<Inventory> findAll() {
        return inventoryDao.findAll();
        }

    // Récupération d'un inventaire par ID
    public Optional<Inventory> findById(Long id) {
        return inventoryDao.findById(id);
    }

    }

