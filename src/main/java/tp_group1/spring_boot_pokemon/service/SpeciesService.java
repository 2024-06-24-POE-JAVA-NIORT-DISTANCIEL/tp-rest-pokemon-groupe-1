package tp_group1.spring_boot_pokemon.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp_group1.spring_boot_pokemon.dao.SpeciesDao;
import tp_group1.spring_boot_pokemon.dao.PokemonDao;
import tp_group1.spring_boot_pokemon.model.Species;

import java.util.List;
import java.util.Optional;

@Service
public class SpeciesService {

    @Autowired
    private SpeciesDao speciesDao;

    @Autowired
    private PokemonDao pokemonDao;

    // Save a species
    @Transactional
    public Species save(Species species) {
        return speciesDao.save(species);
    }

    // Find a species by ID
    public Optional<Species> findById(Long id) {
        return speciesDao.findById(id);
    }

    // Find species by pokemon ID

    public List<Species> findSpeciesByPokemonId(Long pokemonId) {
        return speciesDao.findByPokemonsId(pokemonId);
    }

    // Find species by attack ID
    public List<Species> findSpeciesByAttackId(Long attackId) {
        return speciesDao.findByAttacksId(attackId);
    }

    // Find all species
    public List<Species> findAllSpecies() {
        return speciesDao.findAll();
    }

    // Delete a species by ID, checking if linked to any Pokémon
    @Transactional
    public void deleteById(Long id) {
        if (pokemonDao.findBySpeciesId(id).isEmpty()) {
            speciesDao.deleteById(id);
        } else {
            throw new IllegalStateException("Species is linked to a Pokémon and cannot be deleted.");
        }
    }
}
