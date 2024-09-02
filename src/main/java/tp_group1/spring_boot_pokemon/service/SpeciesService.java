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


    @Transactional
    public Species save(Species species) { return speciesDao.save(species);}

    public Species update(Long SpecieId, Species speciesUpdate) {
        return speciesDao.findById(SpecieId)
                .map(species -> {
                    species.setSpecieName(speciesUpdate.getSpecieName());
                    species.setSpecieType(speciesUpdate.getSpecieType());
                    species.setInitialHealthPoints(speciesUpdate.getInitialHealthPoints());
                    species.setAttacks(speciesUpdate.getAttacks());
                    species.setPokemons(speciesUpdate.getPokemons());
                    return speciesDao.save(species);
                })
                .orElseGet(() -> {
                    speciesUpdate.setId(SpecieId);
                    return speciesDao.save(speciesUpdate);
                });
    }

    public List<Species> findAllSpecies() { return speciesDao.findAll(); }

    public Optional<Species> findById(Long SpecieId) { return speciesDao.findById(SpecieId);}

    public List<Species> findSpeciesByPokemonId(Long pokemonId) { return speciesDao.findByPokemonsId(pokemonId);}

    public List<Species> findSpeciesByAttackId(Long attackId) { return speciesDao.findByAttackId(attackId);}

    @Transactional
    public void deleteById(Long SpecieId) { speciesDao.deleteById(SpecieId); }
}
