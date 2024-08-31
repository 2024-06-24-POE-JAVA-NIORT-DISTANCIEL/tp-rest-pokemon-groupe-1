package tp_group1.spring_boot_pokemon.restController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import tp_group1.spring_boot_pokemon.model.Attack;
import tp_group1.spring_boot_pokemon.model.Pokemon;
import tp_group1.spring_boot_pokemon.service.AttackService;
import tp_group1.spring_boot_pokemon.service.PokemonService;

import java.util.*;

@RestController
@RequestMapping("/pokemons")
public class PokemonRestController {
    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private AttackService attackService;

    //GET - trouver un pokemon par son id
    @GetMapping("/{PokemonId}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable Long PokemonId) {
        Optional<Pokemon> pokemon = pokemonService.findById(PokemonId);
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
    @DeleteMapping("/{PokemonId}")
    public ResponseEntity<Void> deletePokemonById(@PathVariable Long PokemonId) {
        pokemonService.findById(PokemonId);
        if(pokemonService.findById(PokemonId).isPresent()) {
            pokemonService.deleteById(PokemonId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //PUT - mettre à jour un pokemon existant par son id
    @PutMapping("/{PokemonId}")
    public ResponseEntity<Pokemon> updatePokemon(@PathVariable Long PokemonId, @RequestBody Pokemon pokemon) {
        pokemon.setId(PokemonId);
        Pokemon savedPokemon = pokemonService.save(pokemon);
        return ResponseEntity.ok(savedPokemon);
    }

    //POST - pour assigner une attaque déjà créée à des pokemons existants
    @PostMapping("/assignPokemonstoAttack/{AttackId}")
    public ResponseEntity<Void> assignPokemonsToAttck(@PathVariable Long AttackId, @RequestBody List<Long> PokemonIds) {
        //trouver une attaque
        Optional<Attack> plannedAttack = attackService.findById(AttackId);
        if(plannedAttack.isEmpty()) {
            throw new RuntimeException("L'attaque avec l'Id " + AttackId + " n'existe pas.");
        }
        Attack attack = plannedAttack.get();
        //trouver les pokemons par leurs id dans une liste
        List<Pokemon> pokemons = pokemonService.findAllById(PokemonIds);
        if(pokemons.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        //assigner les pokemons à cette attaque
        attackService.addPokemonsToAttack(attack, pokemons);
        return ResponseEntity.ok().build();
    }

}
