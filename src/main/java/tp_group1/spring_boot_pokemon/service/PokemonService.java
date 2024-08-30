package tp_group1.spring_boot_pokemon.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp_group1.spring_boot_pokemon.dao.PokemonDao;
import tp_group1.spring_boot_pokemon.dto.PokemonDto;
import tp_group1.spring_boot_pokemon.model.Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    @Autowired
    private PokemonDto pokemonDto;
    @Autowired
    private PokemonDao pokemonDao;

    //methode save
    @Transactional
    public PokemonDto save(Pokemon pokemon) {
        //save the pokemon entity using the repository
        Pokemon pokemonSaved = pokemonDao.save(pokemon);
        //convert the saved entity to a Dto
        return toDto(pokemonSaved);
    }

    //methode find by id
    public Optional<PokemonDto> findById(Long id) {
        // Find the entity by ID
        Optional<Pokemon> optionalPokemon = pokemonDao.findById(id);
        // If the entity is present, convert it to a DTO
        return optionalPokemon.map(this::toDto);
    }

    //methode find all the pokemons
    public List<PokemonDto> findAll() {
        //find all pokemon entities
        List<Pokemon> pokemons = pokemonDao.findAll();
        //convert each pokemon entity to a pokemon dto in a list
        return pokemons.stream()
                        .map(this::toDto)
                        .collect(Collectors.toList());
    }

    //methode delete by id
    @Transactional
    public void deleteById(Long id) {
        pokemonDao.deleteById(id);
    }

    //transform an entity to a DTO
    private PokemonDto toDto(Pokemon entity) {
        if(entity == null) {
            return null;
        }
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(entity.getId());
        return pokemonDto;
    }

    //transform a Dto to an entity
    private Pokemon toEntity(PokemonDto dto) {
        if(dto == null) {
            return null;
        }
        Pokemon entity = new Pokemon();
        entity.setId(dto.getId());
        return entity;
    }

    //transform a list of entities to a list of Dtos
    private List<PokemonDto> toDtos(List<Pokemon> entities) {
        List<PokemonDto> dtos = new ArrayList<>();
        for(Pokemon entity : entities) {
            dtos.add(toDto((entity)));
        }
        return dtos;
    }
}
