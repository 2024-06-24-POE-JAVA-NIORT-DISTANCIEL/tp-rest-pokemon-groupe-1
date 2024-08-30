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

    // Find all species
    public List<Species> findAll() {
        return speciesDao.findAll();
    }

    // Delete a species by ID, checking if linked to any Pokémon
    @Transactional
    public void deleteById(Long id) throws Exception {
        if (pokemonDao.findByTrainerId(id).isEmpty()) {  // Assuming trainerId is used to find linked Pokémon, adjust as necessary
            speciesDao.deleteById(id);
        } else {
            throw new Exception("Cannot delete species linked to Pokémon");
        }
    }
}
