package tp_group1.spring_boot_pokemon;


import jakarta.inject.Inject;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import tp_group1.spring_boot_pokemon.dao.InventoryDao;
import tp_group1.spring_boot_pokemon.dao.ItemDao;
import tp_group1.spring_boot_pokemon.dao.TrainerDao;
import tp_group1.spring_boot_pokemon.model.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ItemDaoTest {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private InventoryDao inventoryDao;

    @Autowired
    private TrainerDao trainerDao;

    @Enumerated(EnumType.STRING)
//    private ItemType itemType;


    private Item itemUn;
    private Item itemDeux;
    private Trainer trainer;

    @BeforeEach
    void setUp() {
        // Créer un objet et un dresseur pour les tests
        itemUn = new Item(1L, "Potion", 50, null, null);
        itemDeux = new Item(2L, "Pokéball", 30, ItemType.EAU, null);

        Trainer trainer = new Trainer(1L, "Sasha", "password", 200, null, null);
        trainer.setWallet(100);

        // Sauvegarder les entités dans la base de données
        itemDao.save(itemUn);
        itemDao.save(itemDeux);

        trainerDao.save(trainer);
    }


    @Test
    void findItemById() {
    Item item = itemDao.findById(itemUn.getId()).orElse(null);
    assertNotNull(item);
    assertEquals(itemUn.getItemName(), item.getItemName());
    }


//    @Test
//    public void testItemExistsInTrainerInventory() {
        // Récupérer le dresseur depuis la base de données
//        Optional<Trainer> foundTrainer = trainerDao.findById(trainer.getId());
//        assertTrue(foundTrainer.isPresent());
//
//        // Vérifier que l'objet est dans l'inventaire du dresseur
//        List<Item> inventory = foundTrainer.get().getItems();
//        assertNotNull(inventory);
//        assertTrue(inventory.contains(itemUn), "The item should be in the trainer's inventory.");

// }
    @Test
    public void testItemExistsInInventory() {
        Inventory inventory = new Inventory(null, 0, null, null);
        inventoryDao.save(inventory);
        assertNotNull(inventory.getId());
//        inventory.setItem(itemUn);
//        inventoryDao.save(inventory);


        // Ajout d'inventaire à la liste
        Set<Inventory> inventories = new HashSet<>();
        itemUn.setInventories(inventory.getItem().getInventories());
        Item itemSaved = itemDao.save(itemUn);
        assertNotNull(itemSaved.getId());



        // Vérification
//        List<Item> inventorySaved = itemDao.findByInventoriesId(inventory.getId());
//        assertNotNull(inventorySaved);
//        assertFalse(inventorySaved.isEmpty());
//        assertEquals(1, inventorySaved.size());
    }




}
