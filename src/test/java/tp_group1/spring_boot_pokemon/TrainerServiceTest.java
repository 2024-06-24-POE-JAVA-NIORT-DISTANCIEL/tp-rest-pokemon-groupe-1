package tp_group1.spring_boot_pokemon;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tp_group1.spring_boot_pokemon.service.TrainerService;
import tp_group1.spring_boot_pokemon.model.Trainer;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TrainerServiceTest {
    @Autowired
    private TrainerService trainerService;

    @Test
    public void testSaveTrainer() {
        Trainer trainerSaved = new Trainer(null, "Ziky", "12", 100, null, null);
        Trainer savedTrainer = trainerService.save(trainerSaved);

        // Vérifier que l'ID a été généré et est non-null
        assertNotNull(savedTrainer.getId());
        assertTrue(savedTrainer.getId() > 0);
    }

    @Test
    public void testSaveTrainerWithSpecifiedWallet() {
        // Création d'un Trainer avec wallet spécifié
        Trainer trainerWithSpecifiedWallet = new Trainer(null, "Ziky", "12", null, null, null);

        // Sauvegarde du Trainer
        Trainer savedTrainer = trainerService.save(trainerWithSpecifiedWallet);

        // Vérification que l'ID a été généré
        assertNotNull(savedTrainer.getId());
        assertTrue(savedTrainer.getId() > 0);

        // Vérification que le wallet est bien défini à 50
        assertEquals(Integer.valueOf(100), savedTrainer.getWallet());
    }
}
