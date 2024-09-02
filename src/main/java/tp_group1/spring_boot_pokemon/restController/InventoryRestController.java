package tp_group1.spring_boot_pokemon.restController;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp_group1.spring_boot_pokemon.model.Inventory;
import tp_group1.spring_boot_pokemon.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Inventory savedInventory = inventoryService.addInventory(inventory);
        return ResponseEntity.ok(savedInventory);
    }

}
