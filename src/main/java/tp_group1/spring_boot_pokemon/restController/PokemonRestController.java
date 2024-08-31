package tp_group1.spring_boot_pokemon.restController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import tp_group1.spring_boot_pokemon.dao.PokemonDao;
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
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable Long id) {
        Optional<Pokemon> pokemon = pokemonService.findById(id);
        return pokemon.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //GET ALL
    @GetMapping
    public List<Pokemon> getAllPokemons() {
        return pokemonService.findAll();
    }

    //POST
    @PostMapping
    public Pokemon save(@RequestBody Pokemon pokemon) {
        return pokemonService.save(pokemon);
    }

    //DELETE BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemonById(@PathVariable Long id) {
        pokemonService.findById(id);
        if(pokemonService.findById(id).isPresent()) {
            pokemonService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
