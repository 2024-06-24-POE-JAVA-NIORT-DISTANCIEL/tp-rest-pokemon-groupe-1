package tp_group1.spring_boot_pokemon.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp_group1.spring_boot_pokemon.dao.AttackDao;
import tp_group1.spring_boot_pokemon.model.Attack;

import java.util.List;
import java.util.Optional;

@Service
public class AttackService {
    @Autowired
    private AttackDao attackDao;

    //methode save an attack
    @Transactional
    public Attack save(Attack attack) {
        return attackDao.save(attack);
    }

    //methode find an attack by id
    public Optional<Attack> findById(Long id) {
        return attackDao.findById(id);
    }

    //find all attacks
    public List<Attack> findAll() {
        return attackDao.findAll();
    }

    //delete an attack by id
    @Transactional
    public void deleteById(Long id) {
        attackDao.deleteById(id);
    }

}
