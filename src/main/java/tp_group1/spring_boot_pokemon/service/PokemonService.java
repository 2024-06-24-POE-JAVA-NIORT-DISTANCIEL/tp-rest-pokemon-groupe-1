package tp_group1.spring_boot_pokemon.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp_group1.spring_boot_pokemon.dao.PokemonDao;
import tp_group1.spring_boot_pokemon.model.Pokemon;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    private PokemonDao pokemonDao;

    //methode pour créer et sauvegarder un pokemon ou mettre à jour un pokemon existant
    @Transactional
    public Pokemon save(Pokemon pokemon) {
        //verifier si l'id existe
        if(pokemon.getId() != null && pokemonDao.existsById(pokemon.getId())) {
            //mettre a jour le pokemon existant
            return update(pokemon.getId(), pokemon);
        } else { //si l'id n'existe pas, créer un nouveau pokemon
            return pokemonDao.save(pokemon);
        }
    }

    //methode pour trouver un pokemon par son id
    public Optional<Pokemon> findById(Long PokemonId) {
        return pokemonDao.findById(PokemonId);
    }

    //methode pour trouver tous les pokemons
    public List<Pokemon> findAll() {
        return pokemonDao.findAll();
    }

    //methode pour supprimer un pokemon par son id
    @Transactional
    public void deleteById(Long PokemonId) {
        pokemonDao.deleteById(PokemonId);
    }

    //methode pour mettre à jour un pokemon existant par id
    public Pokemon update(Long PokemonId, Pokemon newPokemonData) {
        return pokemonDao.findById(PokemonId)
                .map(existingPokemon -> {
            //mettre à jour les differents champs du pokemon
            existingPokemon.setName(newPokemonData.getName());
            existingPokemon.setLevel(newPokemonData.getLevel());
            existingPokemon.setExperience(newPokemonData.getExperience());
            existingPokemon.setHealthPoints(newPokemonData.getHealthPoints());
            existingPokemon.setMaxHealthPoints(newPokemonData.getMaxHealthPoints());
            existingPokemon.setSpecies(newPokemonData.getSpecies());
            existingPokemon.setTrainer(newPokemonData.getTrainer());

            return pokemonDao.save(existingPokemon);
        })
                .orElseThrow();
    }

    //methode pour trouver tous les pokemons par ids
    public List<Pokemon> findAllById(List<Long> pokemonIds) {
        return pokemonDao.findAllById(pokemonIds);
    }

    // Trouver un Pokémon avec ses attaques
    public Optional<Pokemon> findWithAttacksById(Long pokemonId) {
        return pokemonDao.findById(pokemonId);
    }
}
