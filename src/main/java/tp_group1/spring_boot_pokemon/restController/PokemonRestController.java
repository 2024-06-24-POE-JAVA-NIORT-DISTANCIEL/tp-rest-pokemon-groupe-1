package tp_group1.spring_boot_pokemon.restController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import tp_group1.spring_boot_pokemon.dto.PokemonDto;
import tp_group1.spring_boot_pokemon.model.Pokemon;
import tp_group1.spring_boot_pokemon.service.PokemonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pokemons")
public class PokemonRestController {
    @Autowired
    private PokemonService pokemonService;

    //GET
    @GetMapping("/{id}")
    public ResponseEntity<PokemonDto> getPokemonById(@PathVariable Long id) {
        Optional<PokemonDto> pokemonDto = pokemonService.findById(id);
        return pokemonDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //GET ALL
    @GetMapping
    public List<PokemonDto> getAllPokemons() {
        return pokemonService.findAll();
    }

    //POST
    @PostMapping
    public ResponseEntity<PokemonDto> save(@RequestBody Pokemon pokemon) {
        PokemonDto pokemonDtoSaved = pokemonService.save(pokemon);
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemonDtoSaved);
    }

    //DELETE BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemonById(@PathVariable Long id) {
        Optional<PokemonDto> pokemonDto = pokemonService.findById(id);
        if (pokemonDto.isPresent()) {
            pokemonService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
