package tp_group1.spring_boot_pokemon.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp_group1.spring_boot_pokemon.dao.AttackDao;
import tp_group1.spring_boot_pokemon.model.Attack;
import tp_group1.spring_boot_pokemon.model.Pokemon;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AttackService {
    @Autowired
    private AttackDao attackDao;

    @Autowired
    private PokemonService pokemonService;

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
    public Optional<Attack> findById(Long AttackId) {
        return attackDao.findById(AttackId);
    }

    //methode pour trouver toutes les attaques
    public List<Attack> findAll() {
        return attackDao.findAll();
    }

    //methode pour supprimer une attaque par son id
    @Transactional
    public void deleteById(Long AttackId) {
        attackDao.deleteById(AttackId);
    }

    //methode pour mettre à jour un pokemon existant par son id
    public Attack update(Long AttackId, Attack newAttackData) {
        return attackDao.findById(AttackId)
                .map(existingAttack -> {
                    existingAttack.setAttackName(newAttackData.getAttackName());
                    existingAttack.setType(newAttackData.getType());
                    existingAttack.setDamage(newAttackData.getDamage());
                    existingAttack.setPokemons(newAttackData.getPokemons());
                    existingAttack.setSpecies(newAttackData.getSpecies());
                    return attackDao.save(existingAttack);
                }) .orElseThrow();

    }

    @Transactional
    public void addPokemonsToAttack(Attack attack, List<Pokemon> pokemons) {
        //récupérer les pokemons dans un tableau
        Set<Pokemon> savedPokemons = new HashSet<>(attack.getPokemons());
        savedPokemons.addAll(pokemons);
        attack.setPokemons(savedPokemons);
        //sauvegarder l'attaque
        save(attack);

        //lier l'attaque aux pokemons
        for(Pokemon pokemon : pokemons) {
            Set<Attack> attacks = new HashSet<>(pokemon.getAttacks());
            attacks.add(attack);
            pokemon.setAttacks(attacks);
            pokemonService.save(pokemon);
        }
    }

    //trouver une attack avec ses pokemons
    public Optional<Attack> findWithPokemonsById(Long AttackId) {
        return attackDao.findById(AttackId);
    }

}
