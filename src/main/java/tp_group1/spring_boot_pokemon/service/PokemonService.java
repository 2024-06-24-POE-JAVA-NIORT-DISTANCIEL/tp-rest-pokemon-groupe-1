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

    //methode save
    @Transactional
    public Pokemon save(Pokemon pokemon) {
        return pokemonDao.save(pokemon);
    }

    //methode find by id
    public Optional<Pokemon> findById(Long id) {
        return pokemonDao.findById(id);
    }

    //methode find all the pokemons
    public List<Pokemon> findAll() {
        return pokemonDao.findAll();
    }

    //methode delete by id
    @Transactional
    public void deleteById(Long id) {
        pokemonDao.deleteById(id);
    }
}
