package tp_group1.spring_boot_pokemon;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.aspectj.weaver.IEclipseSourceContext;
import org.checkerframework.checker.units.qual.A;
import tp_group1.spring_boot_pokemon.dao.InventoryDao;
import tp_group1.spring_boot_pokemon.dao.ItemDao;
import tp_group1.spring_boot_pokemon.dao.TrainerDao;
import tp_group1.spring_boot_pokemon.model.Inventory;
import tp_group1.spring_boot_pokemon.model.Item;
import tp_group1.spring_boot_pokemon.model.ItemType;
import tp_group1.spring_boot_pokemon.model.Trainer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InventoryDaoTest {

    @Autowired
    private InventoryDao inventoryDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private TrainerDao trainerDao;

    private Trainer trainer;
    private Item item;


    @BeforeEach
    void setUp() {
        // Initialiser un dresseur, et un objet
        trainer = new Trainer(null, "Ash", "HashedPassword", 100, null, null);
        trainerDao.save(trainer);

        item = new Item(null, "Potion", 50, ItemType.ATTAQUE, null);
        itemDao.save(item);
    }


    @Test
    void testAddInventory() {
        Inventory inventory = new Inventory(null, 10, item, trainer);
        Inventory savedInventory = inventoryDao.save(inventory);

        assertNotNull(savedInventory.getId());
        assertEquals(10, savedInventory.getQuantity());
        assertEquals(trainer.getId(), savedInventory.getTrainer().getId());
    }

    @Test
    void testFindInventoryByTrainer() {
        Inventory inventory = new Inventory(null, 10, item, trainer);
        inventoryDao.save(inventory);

        List<Inventory> inventories = inventoryDao.findByTrainerId(trainer.getId());
        assertFalse(inventories.isEmpty());
        assertEquals(1, inventories.size());
        assertEquals(item.getItemName(), inventories.get(0).getItem().getItemName());

    }

}
