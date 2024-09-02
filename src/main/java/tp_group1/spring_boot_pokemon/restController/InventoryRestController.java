package tp_group1.spring_boot_pokemon.restController;

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
@RequestMapping("/inventory")
public class InventoryRestController {

    @Autowired
    private InventoryService inventoryService;

    // On récupére l'inventaire d'un dresseur
    @GetMapping("/trainer/{trainerId}")
    public List<Inventory> getInventoryByTrainer(@PathVariable Long trainerId) {
        return inventoryService.findInventoryByTrainer(trainerId);
    }

    // Ajout d'objet à l'inventaire
    @PostMapping("/add")
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {
        try {
            Inventory savedInventory = inventoryService.addInventory(inventory);
            return ResponseEntity.ok(savedInventory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // Récupérer l'ensemble de l'inventaire
    @GetMapping
    public List<Inventory> getAllInventories() {
        return inventoryService.findAll();
    }

    //
    @GetMapping("/{id}")
    public Optional<Inventory> getInventoryById(@PathVariable Long id) {
        return inventoryService.findById(id);
    }
}

