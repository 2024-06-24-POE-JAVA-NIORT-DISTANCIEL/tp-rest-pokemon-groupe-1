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

    //methode pour créer une nouvelle attaque et sauvegarder ou mettre à jour une attaque existante par son id
    @Transactional
    public Attack save(Attack attack) {
        //verifier si l'id existe
        if(attack.getId() != null) {
            return update(attack.getId(), attack);
        } else { //si l'id n'existe pas, créer une nouvelle attaque
            return attackDao.save(attack);
        }
    }

    //methode pour trouver une attaque par son id
    public Optional<Attack> findById(Long id) {
        return attackDao.findById(id);
    }

    //methode pour trouver toutes les attaques
    public List<Attack> findAll() {
        return attackDao.findAll();
    }

    //methode pour supprimer une attaque par son id
    @Transactional
    public void deleteById(Long id) {
        attackDao.deleteById(id);
    }

    //methode pour mettre à jour un pokemon existant par son id
    public Attack update(Long id, Attack newAttackData) {
        return attackDao.findById(id)
                .map(existingAttack -> {
                    existingAttack.setAttackName(newAttackData.getAttackName());
                    existingAttack.setType(newAttackData.getType());
                    existingAttack.setDamage(newAttackData.getDamage());
                    existingAttack.setPokemons(newAttackData.getPokemons());
                    existingAttack.setSpecies(newAttackData.getSpecies());
                    return attackDao.save(existingAttack);
                }) .orElseThrow();

    }

}
