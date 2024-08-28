package tp_group1.spring_boot_pokemon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tp_group1.spring_boot_pokemon.dao.TrainerDao;
import tp_group1.spring_boot_pokemon.model.Trainer;

@SpringBootTest
public class TestTrainerDao {
    @Autowired
    private TrainerDao trainerDao;

    @Test
    public void testSaveTrainer() {
        Trainer trainer1 = new Trainer(null, "Ivy", "12", 100);
        trainerDao.save(trainer1);
        Assertions.assertEquals(1,trainer1.getId());
    }

    @Test
    public void testFindById() {
        Trainer trainer1 = new Trainer(1L, "Ivy", "12", 100);
        trainerDao.findById(trainer1.getId());
        Assertions.assertEquals(1L, trainer1.getId());
    }

    @Test
    public void testDeleteById() {
        Trainer trainer1 = new Trainer(1L, "Ivy", "12", 100);
        Trainer trainerSaved = trainerDao.save(trainer1);
        trainerDao.deleteById(trainerSaved.getId());
        Assertions.assertTrue(trainerDao.findById(trainer1.getId()).isEmpty());
    }
}
