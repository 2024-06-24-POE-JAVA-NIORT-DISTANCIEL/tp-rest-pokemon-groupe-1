package tp_group1.spring_boot_pokemon.service;

import jakarta.transaction.Transactional;
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

    //créer un combat
    @Transactional
    public Fight startFight(Long pokemonId1, Long pokemonId2) {
        // Vérifier si les pokemons existent avec leur ID
        PokemonDto savedPokemon1 = pokemonService.findById(pokemonId1);
        PokemonDto savedPokemon2 = pokemonService.findById(pokemonId2);

        if (savedPokemon1 == null || savedPokemon2 == null) {
            throw new IllegalArgumentException("L'un des deux pokemons n'existe pas !");
        }

        // Conversion des DTOs en entités
        Pokemon pokemon1 = pokemonService.dtoToEntity(savedPokemon1);
        Pokemon pokemon2 = pokemonService.dtoToEntity(savedPokemon2);

        // Vérifier si ces pokemons n'ont pas les points de vie = 0
        if (pokemon1.getHealthPoints() <= 0 || pokemon2.getHealthPoints() <= 0) {
            throw new IllegalArgumentException("L'un de ces pokémons n'a plus de points de vie !");
        }

        //initialiser le combat
        Fight fight = new Fight();
        fight.setPokemonA(pokemon1);
        fight.setPokemonB(pokemon2);

        //choix aléatoire du premier attaquant entre les deux pokemons
        Pokemon attacker = Math.random() < 0.5 ? pokemon1 : pokemon2;
        Pokemon defender = attacker == pokemon1 ? pokemon2 : pokemon1;

        while(pokemon1.getHealthPoints() > 0 && pokemon2.getHealthPoints() >0) {
            // Chaque Pokemon perd 5 points de vie par tour
            defender.setHealthPoints(defender.getHealthPoints() - 5);
            // Vérification du vainqueur
            if (defender.getHealthPoints() <= 0) {
                fight.setWinner(attacker.getName());
                fight.setLoser(defender.getName());
                fight.setResult(attacker.getName() + " a gagné le combat !");
                break;
            }
            // Inverser les rôles pour le prochain tour
            Pokemon temp = attacker;
            attacker = defender;
            defender = temp;
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
