package tp_group1.spring_boot_pokemon.service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp_group1.spring_boot_pokemon.dao.FightDao;
import tp_group1.spring_boot_pokemon.dto.PokemonDto;
import tp_group1.spring_boot_pokemon.model.Fight;
import tp_group1.spring_boot_pokemon.model.Pokemon;

import java.util.List;
import java.util.Optional;

@Service
public class FightService {
    @Autowired
    private FightDao fightDao;
    @Autowired
    private PokemonService pokemonService;

    private static final Logger LOGGER = LoggerFactory.getLogger(FightService.class);

    //créer un combat
    @Transactional
    public Fight startFight(Long pokemonId1, Long pokemonId2) {
        LOGGER.info("Début du combat entre les Pokémon avec ID: {} et {}", pokemonId1, pokemonId2);

        // Vérifier si les pokemons existent avec leur ID
        PokemonDto savedPokemon1 = pokemonService.findById(pokemonId1);
        PokemonDto savedPokemon2 = pokemonService.findById(pokemonId2);

        if (savedPokemon1 == null || savedPokemon2 == null) {
            LOGGER.error("L'un des deux pokémons n'existe pas ! ID1: {}, ID2: {}", pokemonId1, pokemonId2);
            throw new IllegalArgumentException("L'un des deux pokemons n'existe pas !");
        }

        // Conversion des DTOs en entités
        Pokemon pokemon1 = pokemonService.dtoToEntity(savedPokemon1);
        Pokemon pokemon2 = pokemonService.dtoToEntity(savedPokemon2);

        // Vérifier si ces pokemons n'ont pas les points de vie = 0
        if (pokemon1.getHealthPoints() <= 0 || pokemon2.getHealthPoints() <= 0) {
            LOGGER.error("L'un de ces pokémons n'a plus de points de vie ! {}: {} HP, {}: {} HP",
                    pokemon1.getName(), pokemon1.getHealthPoints(),
                    pokemon2.getName(), pokemon2.getHealthPoints());
            throw new IllegalArgumentException("L'un de ces pokémons n'a plus de points de vie !");
        }

        // Initialiser les points de vie actuels à leur valeur maximale
        pokemon1.setHealthPoints(pokemon1.getMaxHealthPoints());
        pokemon2.setHealthPoints(pokemon2.getMaxHealthPoints());

        LOGGER.info("Points de vie actuels définis: {} (HP: {}), {} (HP: {})",
                pokemon1.getName(), pokemon1.getHealthPoints(),
                pokemon2.getName(), pokemon2.getHealthPoints());

        //initialiser le combat
        Fight fight = new Fight();
        fight.setPokemonA(pokemon1);
        fight.setPokemonB(pokemon2);

        //choix aléatoire du premier attaquant entre les deux pokemons
        Pokemon attacker = Math.random() < 0.5 ? pokemon1 : pokemon2;
        Pokemon defender = attacker == pokemon1 ? pokemon2 : pokemon1;

        LOGGER.info("{} commence l'attaque contre {}", attacker.getName(), defender.getName());
        //boucle du combat
        while(pokemon1.getHealthPoints() >= 0 && pokemon2.getHealthPoints() >=0) {
            LOGGER.info("{} attaque {} et inflige 5 points de dégâts", attacker.getName(), defender.getName());
            // Calcul des dégâts infligés : (niveau du pokemon / 10) * nombre de points de dégâts de l’attaque * modificateur d’attaque
            // Modificateur de combat
            double damageFight = 1.0;
            // Points de dégâts de l'attaque
            int baseDamage = attacker.getSpecies().getAttack().getDamage();
            // Facteur basé sur le niveau du Pokémon
            double levelFactor = attacker.getLevel() / 10.0;
            // Calcul final des dégâts
            int damage = (int) (levelFactor * baseDamage * damageFight);
            //pour mettre à 0 et ne pas avoir de points négatifs
            int newHealthPoints = Math.max(defender.getHealthPoints() - damage, 0);
            defender.setMaxHealthPoints(newHealthPoints);
            LOGGER.info("{} a maintenant {} HP", defender.getName(), defender.getHealthPoints());

            // Vérification du vainqueur
            if (defender.getHealthPoints() <= 0) {
                LOGGER.info("{} a gagné le combat contre {}", attacker.getName(), defender.getName());
                fight.setWinner(attacker.getName());
                fight.setLoser(defender.getName());
                fight.setResult(attacker.getName() + " a gagné le combat !");
                break;
            }
            // Inverser les rôles pour le prochain tour
            Pokemon temp = attacker;
            attacker = defender;
            defender = temp;
            LOGGER.info("Maintenant, {} attaque {}", attacker.getName(), defender.getName());
        }

        // Sauvegarder le combat
        return fightDao.save(fight);
    }



    //trouver un combat par id
    public Optional<Fight> findById(Long id) {
        return fightDao.findById(id);
    }

    //trouver tous les combats
    public List<Fight> findAll() {
        return fightDao.findAll();
    }

    //supprimer un combat par son id
    @Transactional
    public void deleteById(Long id) {
        fightDao.deleteById(id);
    }


}
