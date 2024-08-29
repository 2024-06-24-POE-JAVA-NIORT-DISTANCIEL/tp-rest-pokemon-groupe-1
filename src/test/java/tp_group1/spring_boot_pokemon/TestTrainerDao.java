package tp_group1.spring_boot_pokemon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tp_group1.spring_boot_pokemon.dao.TrainerDao;
import tp_group1.spring_boot_pokemon.model.Trainer;

import java.util.List;

@SpringBootTest
public class TestTrainerDao {

    @Autowired
    private TrainerDao trainerDao;

    @Test
    public void testSaveTrainer() {
        Trainer trainerSaved = new Trainer(null, "Ziky", "12", 100, null);
        Trainer savedTrainer = trainerDao.save(trainerSaved);

        // Vérifier que l'ID a été généré et est non-null
        assertNotNull(savedTrainer.getId());
        assertTrue(savedTrainer.getId() > 0);
    }

    @Test
    public void testFindById() {
        Trainer trainer = new Trainer(1L, "Ziky", "12", 100,null);
        Trainer savedTrainer = trainerDao.save(trainer);

        Trainer foundTrainer = trainerDao.findById(savedTrainer.getId()).orElse(null);

        assertNotNull(foundTrainer);
        assertEquals(savedTrainer.getId(), foundTrainer.getId());
    }

    @Test
    public void testDeleteById() {
        Trainer trainer = new Trainer(1L, "Ivy", "12", 100, null);
        Trainer savedTrainer = trainerDao.save(trainer);
        trainerDao.deleteById(savedTrainer.getId());

        assertTrue(trainerDao.findById(savedTrainer.getId()).isEmpty());
    }

    @Test
    public void testFindByUsernameContainingIgnoringCase() {
        Trainer trainer1 = new Trainer(1L, "Theo", "12", 100, null);
        Trainer trainer2 = new Trainer(2L, "Ivyrest", "12", 100, null);
        Trainer trainer3 = new Trainer(3L, "Ricarduro", "12", 100, null);
        trainerDao.save(trainer1);
        trainerDao.save(trainer2);
        trainerDao.save(trainer3);

        List<Trainer> foundTrainers = trainerDao.findByUsernameContainingIgnoringCase("IvY");
        assertEquals(1, foundTrainers.size());
        assertEquals("Ivyrest", foundTrainers.get(0).getUsername());
    }

    @Test
    public void testFindAll() {
        Trainer trainer1 = new Trainer(1L, "Martin", "12", 100, null);
        Trainer trainer2 = new Trainer(2L, "Catherine", "12", 100, null);
        Trainer trainer3 = new Trainer(3L, "Edmundo", "12", 100, null);

        trainerDao.save(trainer1);
        trainerDao.save(trainer2);
        trainerDao.save(trainer3);

        List<Trainer> trainers = trainerDao.findAll();

        // Vérifier que la liste contient bien les 3 entraîneurs
        assertEquals(3, trainers.size());
        // Vérifier que tous les entraîneurs sont présents
        assertTrue(trainers.stream().anyMatch(trainer -> "Martin".equals(trainer.getUsername())));
        assertTrue(trainers.stream().anyMatch(trainer -> "Catherine".equals(trainer.getUsername())));
        assertTrue(trainers.stream().anyMatch(trainer -> "Edmundo".equals(trainer.getUsername())));
    }

    @Test
    public void testFindAllByOrderByUsernameAsc() {
        Trainer trainer1 = new Trainer(1L, "Theo", "12", 100, null);
        Trainer trainer2 = new Trainer(2L, "Ivy", "12", 100, null);
        Trainer trainer3 = new Trainer(3L, "Ricarduro", "12", 100, null);

        trainerDao.save(trainer1);
        trainerDao.save(trainer2);
        trainerDao.save(trainer3);

        List<Trainer> trainersAsc = trainerDao.findAllByOrderByUsernameAsc();

        // Vérifier l'ordre croissant
        assertEquals("Ivy", trainersAsc.get(0).getUsername());
        assertEquals("Ricarduro", trainersAsc.get(1).getUsername());
        assertEquals("Theo", trainersAsc.get(2).getUsername());
    }

    @Test
    public void testFindAllByOrderByUsernameDesc() {
        Trainer trainer1 = new Trainer(1L, "Clarence", "12", 100, null);
        Trainer trainer2 = new Trainer(2L, "Lucas", "12", 100, null);
        Trainer trainer3 = new Trainer(3L, "Framboisier", "12", 100, null);

        trainerDao.save(trainer1);
        trainerDao.save(trainer2);
        trainerDao.save(trainer3);
        List<Trainer> trainersDesc = trainerDao.findAllByOrderByUsernameDesc();

        // Vérifier l'ordre décroissant
        assertEquals("Ziky", trainersDesc.get(0).getUsername());
        assertEquals("Lucas", trainersDesc.get(1).getUsername());
        assertEquals("Framboisier", trainersDesc.get(2).getUsername());
    }
}
