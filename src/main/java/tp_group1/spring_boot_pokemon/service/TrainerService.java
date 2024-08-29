package tp_group1.spring_boot_pokemon.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tp_group1.spring_boot_pokemon.dao.TrainerDao;
import tp_group1.spring_boot_pokemon.model.Trainer;


import java.util.List;
import java.util.Optional;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

@Service
public class TrainerService {
    @Autowired
    private TrainerDao trainerDao;

    //methode save
    @Transactional
    public Trainer save(Trainer trainer) {
        String hashedPassword = hashPassword(trainer.getPassword());
        System.out.println("Mot de passe haché : " + hashedPassword); // Pour debug
        trainer.setPassword(hashedPassword);
        return trainerDao.save(trainer);
    }

    //methode find by
    public Optional<Trainer> findById(Long id) {
        return trainerDao.findById(id);
    }

    //methode pour trouver tous les trainers
    public List<Trainer> findAll() {
        return trainerDao.findAll();
    }

    //delete by id
    @Transactional
    public void deleteById(Long id) {
        trainerDao.deleteById(id);
    }

    // Hachage du mot de passe avec MD5
    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erreur de hachage du mot de passe", e);
        }
    }

}
