package tp_group1.spring_boot_pokemon.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp_group1.spring_boot_pokemon.dao.PokemonDao;
import tp_group1.spring_boot_pokemon.dao.SpeciesDao;
import tp_group1.spring_boot_pokemon.dao.TrainerDao;
import tp_group1.spring_boot_pokemon.dao.AttackDao;
import tp_group1.spring_boot_pokemon.dto.PokemonDto;
import tp_group1.spring_boot_pokemon.model.Pokemon;
import tp_group1.spring_boot_pokemon.model.Species;
import tp_group1.spring_boot_pokemon.model.Trainer;
import tp_group1.spring_boot_pokemon.model.Attack;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PokemonService {

    @Autowired
    private PokemonDao pokemonDao;

    @Autowired
    private SpeciesDao speciesDao;

    @Autowired
    private TrainerDao trainerDao;

    @Autowired
    private AttackDao attackDao;

    @Transactional
    public PokemonDto save(PokemonDto pokemonDto) {
        Species species = null;
        if (pokemonDto.getSpeciesId() != null) {
            species = speciesDao.findById(pokemonDto.getSpeciesId())
                    .orElseThrow(() -> new EntityNotFoundException("Species not found with ID: " + pokemonDto.getSpeciesId()));
        }

        Trainer trainer = null;
        if (pokemonDto.getTrainerId() != null) {
            trainer = trainerDao.findById(pokemonDto.getTrainerId())
                    .orElseThrow(() -> new EntityNotFoundException("Trainer not found with ID: " + pokemonDto.getTrainerId()));
        }

        Set<Attack> attacks = new HashSet<>();
        if (pokemonDto.getAttacks() != null) {
            for (Long attackId : pokemonDto.getAttacks().stream().map(Attack::getId).toList()) {
                Attack attack = attackDao.findById(attackId)
                        .orElseThrow(() -> new EntityNotFoundException("Attack not found with ID: " + attackId));
                attacks.add(attack);
            }
        }

        Pokemon pokemon = new Pokemon(null, pokemonDto.getName(), pokemonDto.getLevel(), pokemonDto.getExperience(), pokemonDto.getHealthPoints(), pokemonDto.getMaxHealthPoints(), trainer, species, attacks);
        initializeHealthPointsIfNeeded(pokemon);
        Pokemon savedPokemon = pokemonDao.save(pokemon);

        // Update the attacks with the new Pokemon
        for (Attack attack : pokemon.getAttacks()) {
            attack.getPokemons().add(savedPokemon);
            attackDao.save(attack);
        }

        return entityToDto(savedPokemon);
    }


    private void initializeHealthPointsIfNeeded(Pokemon pokemon) {
        if (pokemon.getHealthPoints() == null || pokemon.getMaxHealthPoints() == null) {
            pokemon.setMaxHealthPoints(pokemon.getSpecies().getInitialHealthPoints());
            pokemon.setHealthPoints(pokemon.getMaxHealthPoints());
        }
    }

    public PokemonDto findById(Long PokemonId) {
        return pokemonDao.findById(PokemonId)
                .map(this::entityToDto)
                .orElse(null);
    }

    public List<PokemonDto> findAll() {
        return pokemonDao.findAll().stream()
                .map(this::entityToDto)
                .toList();
    }

    @Transactional
    public void deleteById(Long PokemonId) {
        pokemonDao.deleteById(PokemonId);
    }

    public PokemonDto update(Long PokemonId, PokemonDto newPokemonData) {
        Optional<Pokemon> existingPokemon = pokemonDao.findById(PokemonId);
        if (existingPokemon.isEmpty()) {
            throw new EntityNotFoundException("Pokemon not found with ID: " + PokemonId);
        }
        Pokemon pokemon = dtoToEntity(newPokemonData);
        initializeHealthPointsIfNeeded(pokemon);
        return entityToDto(pokemonDao.save(pokemon));
    }

    public PokemonDto entityToDto(Pokemon pokemon) {
        PokemonDto dto = new PokemonDto();
        dto.setId(pokemon.getId());
        dto.setName(pokemon.getName());
        dto.setLevel(pokemon.getLevel());
        dto.setExperience(pokemon.getExperience());
        dto.setHealthPoints(pokemon.getHealthPoints());
        dto.setMaxHealthPoints(pokemon.getMaxHealthPoints());
        if (pokemon.getSpecies() != null) {
            dto.setSpeciesId(pokemon.getSpecies().getId());
        }
        if (pokemon.getTrainer() != null) {
            dto.setTrainerId(pokemon.getTrainer().getId());
        }
        if (pokemon.getAttacks() != null) {
            for (Attack attack : pokemon.getAttacks()) {
                dto.getAttacks().add(attack);
            }
        }
        return dto;
    }

    public Pokemon dtoToEntity(PokemonDto dto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(dto.getId());
        pokemon.setName(dto.getName());
        pokemon.setLevel(dto.getLevel());
        pokemon.setExperience(dto.getExperience());
        pokemon.setHealthPoints(dto.getHealthPoints());
        pokemon.setMaxHealthPoints(dto.getMaxHealthPoints());

        if (dto.getSpeciesId() != null) {
            Species species = speciesDao.findById(dto.getSpeciesId())
                    .orElseThrow(() -> new EntityNotFoundException("Specie not found with ID: " + dto.getSpeciesId()));
            pokemon.setSpecies(species);
        }

        if (dto.getTrainerId() != null) {
            Trainer trainer = trainerDao.findById(dto.getTrainerId())
                    .orElseThrow(() -> new EntityNotFoundException("Trainer not found with ID: " + dto.getTrainerId()));
            pokemon.setTrainer(trainer);
        }

        if (dto.getAttacks() != null) {
            Set<Attack> attacks = new HashSet<>();
            for (Attack attackId : dto.getAttacks()) {
                Attack attack = attackDao.findById(attackId.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Attack not found with ID: " + attackId));
                attacks.add(attack);
            }
            pokemon.setAttacks(attacks);
        }

        return pokemon;
    }

    public List<PokemonDto> findAllById(List<Long> pokemonIds) {
        return pokemonDao.findAllById(pokemonIds).stream()
                .map(this::entityToDto)
                .toList();
    }

    public Optional<PokemonDto> findWithAttacksById(Long pokemonId) {
        return pokemonDao.findByAttacksId(pokemonId).stream()
                .findFirst()
                .map(this::entityToDto);
    }
}
