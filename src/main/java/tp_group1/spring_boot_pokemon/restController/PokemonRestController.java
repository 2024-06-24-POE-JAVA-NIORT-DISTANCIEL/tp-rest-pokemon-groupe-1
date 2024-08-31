package tp_group1.spring_boot_pokemon.restController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import tp_group1.spring_boot_pokemon.model.Pokemon;
import tp_group1.spring_boot_pokemon.service.PokemonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pokemons")
public class PokemonRestController {
    @Autowired
    private PokemonService pokemonService;

    //GET - trouver un pokemon par son id
    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable Long id) {
        Optional<Pokemon> pokemon = pokemonService.findById(id);
        return pokemon.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //GET ALL - trouver tous les pokemons
    @GetMapping
    public List<Pokemon> getAllPokemons() {
        return pokemonService.findAll();
    }

    //POST - créer un nouveau pokemon ou mettre à jour un pokemon existant et sauvegarder
    @PostMapping
    public ResponseEntity<Pokemon>createPokemon(@RequestBody Pokemon pokemon) {
        Pokemon savedPokemon = pokemonService.save(pokemon);
        return ResponseEntity.ok(savedPokemon);
    }

    //DELETE BY ID - supprimer un pokemon par son id
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

    //PUT - mettre à jour un pokemon existant par son id
    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> updatePokemon(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        pokemon.setId(id);
        Pokemon savedPokemon = pokemonService.save(pokemon);
        return ResponseEntity.ok(savedPokemon);
    }

}
