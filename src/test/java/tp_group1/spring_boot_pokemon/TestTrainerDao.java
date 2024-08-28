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

//    @Test
//    public void testSaveTrainer() {
//        Trainer trainer1 = new Trainer(null, "Ivy", "12", 100);
//        trainerDao.save(trainer1);
//        Assertions.assertEquals(1,trainer1.getId());
//    }
//
//    @Test
//    public void testFindById() {
//        Trainer trainer1 = new Trainer(1L, "Ivy", "12", 100);
//        trainerDao.findById(trainer1.getId());
//        Assertions.assertEquals(1L, trainer1.getId());
//    }
//
//    @Test
//    public void testDeleteById() {
//        Trainer trainer1 = new Trainer(1L, "Ivy", "12", 100);
//        Trainer trainerSaved = trainerDao.save(trainer1);
//        trainerDao.deleteById(trainerSaved.getId());
//        Assertions.assertTrue(trainerDao.findById(trainer1.getId()).isEmpty());
//    }
//
//    @Test
//    public void testFindByUsernameContainingIgnoringCase() {
//        Trainer trainer1 = new Trainer(1L, "Ivyrest", "12", 100);
//        Trainer trainer2 = new Trainer(2L, "Theo", "12", 100);
//        Trainer trainer3 = new Trainer(3L, "Ricarduro", "12", 100);
//        trainerDao.save(trainer1);
//        Assertions.assertEquals(1, trainerDao.findByUsernameContainingIgnoringCase("IvY").size());
//    }

    @Test
    public void testFindAll() {
        Trainer trainer1 = new Trainer(null, "Theo");
        Trainer trainer2 = new Trainer(null, "Ivy");
        Trainer trainer3 = new Trainer(null, "Ricarduro");

        trainerDao.save(trainer1);
        trainerDao.save(trainer2);
        trainerDao.save(trainer3);

        // Récupérer tous les trainers sans ordre spécifique
        List<Trainer> trainers = (List<Trainer>) trainerDao.findAll();

        // Vérifier que la liste contient bien les 3 trainers
        assertEquals(3, trainers.size());

        assertEquals( "Ivy", trainers.get(0).getUsername());
    }

    @Test
    public void testFindAllByOrderByUsernameAscAndDesc() {
        Trainer trainer1 = new Trainer(null, "Theo");
        Trainer trainer2 = new Trainer(null, "Ivy");
        Trainer trainer3 = new Trainer(null, "Ricarduro");

        trainerDao.save(trainer1);
        trainerDao.save(trainer2);
        trainerDao.save(trainer3);

        // Récupérer tous les trainers sans ordre spécifique
        List<Trainer> trainers1 = (List<Trainer>) trainerDao.findAllByOrderByUsernameAsc();
        List<Trainer> trainers2 = (List<Trainer>) trainerDao.findAllByOrderByUsernameDesc();

        assertEquals( "Ivy", trainers1.get(0).getUsername());
        assertEquals( "Ricarduro", trainers2.get(0).getUsername());
    }
}
