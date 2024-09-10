package tp_group1.spring_boot_pokemon.restController;
import tp_group1.spring_boot_pokemon.service.ItemService;
import tp_group1.spring_boot_pokemon.service.TrainerService;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp_group1.spring_boot_pokemon.model.Inventory;
import tp_group1.spring_boot_pokemon.model.Item;
import tp_group1.spring_boot_pokemon.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventories")
public class InventoryRestController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private ItemService itemService;

    // trouver l'inventaire d'un dresseur
    @GetMapping("/trainer/{trainerId}")
    public List<Inventory> getInventoryByTrainer(@PathVariable Long trainerId) {
        return inventoryService.findInventoryByTrainer(trainerId);
    }

    // Ajout d'objet à l'inventaire
    @PostMapping
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {
        try {
            // Vérifier si le Trainer existe
            if (inventory.getTrainer() == null || inventory.getTrainer().getId() == null) {
                return ResponseEntity.badRequest().body(null);
            }

            // Vérifier si l'Item existe
            if (inventory.getItem() == null || inventory.getItem().getId() == null) {
                return ResponseEntity.badRequest().body(null);
            }
            Optional<Item> itemOpt = itemService.findById(inventory.getItem().getId());
            if (itemOpt.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            // Si les objets sont valides, sauvegarde l'inventory
            Inventory savedInventory = inventoryService.addInventory(inventory);
            return ResponseEntity.ok(savedInventory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Récupérer tous les inventories
    @GetMapping
    public List<Inventory> getAllInventories() {
        return inventoryService.findAll();
    }

    // Récupérer un inventaire par son ID
    @GetMapping("/{id}")
    public Optional<Inventory> getInventoryById(@PathVariable Long id) {
        return inventoryService.findById(id);
    }

    // Supprimer un inventaire par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryById(@PathVariable Long id) {
        Optional<Inventory> inventory = inventoryService.findById(id);

        if (inventory.isPresent()) {
            inventoryService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


