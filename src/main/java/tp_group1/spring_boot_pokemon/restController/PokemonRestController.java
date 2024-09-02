package tp_group1.spring_boot_pokemon.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp_group1.spring_boot_pokemon.model.Pokemon;
import tp_group1.spring_boot_pokemon.dto.PokemonDto;
import tp_group1.spring_boot_pokemon.model.Attack;
import tp_group1.spring_boot_pokemon.service.AttackService;
import tp_group1.spring_boot_pokemon.service.PokemonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pokemons")
public class PokemonRestController {

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private AttackService attackService;

    @GetMapping("/{PokemonId}")
    public ResponseEntity<PokemonDto> getPokemonById(@PathVariable Long PokemonId) {
        PokemonDto pokemon = pokemonService.findById(PokemonId);
        return pokemon != null ? ResponseEntity.ok(pokemon) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<PokemonDto> getAllPokemons() {
        return pokemonService.findAll();
    }

    @PostMapping
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemon) {
        PokemonDto savedPokemon = pokemonService.save(pokemon);
        return ResponseEntity.ok(savedPokemon);
    }

    @DeleteMapping("/{PokemonId}")
    public ResponseEntity<Void> deletePokemonById(@PathVariable Long PokemonId) {
        if (pokemonService.findById(PokemonId) != null) {
            pokemonService.deleteById(PokemonId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{PokemonId}")
    public ResponseEntity<PokemonDto> updatePokemon(@PathVariable Long PokemonId, @RequestBody PokemonDto pokemon) {
        pokemon.setId(PokemonId);
        PokemonDto savedPokemon = pokemonService.update(PokemonId, pokemon);
        return ResponseEntity.ok(savedPokemon);
    }

    @PostMapping("/assignPokemonstoAttack/{AttackId}")
    public ResponseEntity<Void> assignPokemonsToAttack(@PathVariable Long AttackId, @RequestBody List<Long> PokemonIds) {
        Optional<Attack> plannedAttack = attackService.findById(AttackId);
        if (plannedAttack.isEmpty()) {
            throw new RuntimeException("L'attaque avec l'Id " + AttackId + " n'existe pas.");
        }
        Attack attack = plannedAttack.get();
        List<PokemonDto> pokemons = pokemonService.findAllById(PokemonIds);
        if (pokemons.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        attackService.addPokemonsToAttack(attack, pokemons);
        return ResponseEntity.ok().build();
    }
}
