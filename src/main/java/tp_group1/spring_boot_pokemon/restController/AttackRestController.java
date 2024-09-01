package tp_group1.spring_boot_pokemon.restController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import tp_group1.spring_boot_pokemon.model.Attack;
import tp_group1.spring_boot_pokemon.model.Pokemon;
import tp_group1.spring_boot_pokemon.service.AttackService;
import tp_group1.spring_boot_pokemon.service.PokemonService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/attacks")
public class AttackRestController {
    @Autowired
    private AttackService attackService;

    @Autowired
    private PokemonService pokemonService;

    //GET
    @GetMapping("/{AttackId}")
    public ResponseEntity<Attack> getAttackById(@PathVariable Long AttackId) {
        Optional<Attack> attack = attackService.findById(AttackId);
        return attack.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //GET ALL
    @GetMapping
    public List<Attack> getAllAttacks() {
        return attackService.findAll();
    }

    //POST
    @PostMapping
    public Attack save(@RequestBody Attack attack) {
        return attackService.save(attack);
    }

    //DELETE BY ID
    @DeleteMapping("/{AttackId}")
    public ResponseEntity<Void> deleteAttackById(@PathVariable Long AttackId) {
        attackService.findById(AttackId);
        if(attackService.findById(AttackId).isPresent()) {
            attackService.deleteById(AttackId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //PUT - mettre à jour une attaque existante par son id
    @PutMapping("/{AttackId}")
    public ResponseEntity<Attack> updateAttack(@PathVariable Long id, @RequestBody Attack attack) {
        attack.setId(id);
        try {
            Attack updatedAttack = attackService.save(attack);
            return ResponseEntity.ok(updatedAttack);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //créer une nouvelle attaque et relier deux pokemons déjà existants
    @PostMapping("/createAttackForPokemons")
    public ResponseEntity<Attack> createAttackForPokemons(@RequestParam Long PokemonId1, @RequestParam Long PokemonId2, @RequestBody Attack attack) {
        //récupérer les pokemons
        Optional<Pokemon> pokemon1 = pokemonService.findById(PokemonId1);
        Optional<Pokemon> pokemon2 = pokemonService.findById(PokemonId2);
        //vérifier si pokemon existe
        if (pokemon1.isEmpty() || pokemon2.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        // Obtenir les objets Pokémon à partir des optionals
        Pokemon savedPokemon1 = pokemon1.get();
        Pokemon savedPokemon2 = pokemon2.get();
        //créer tableau pokemons
        Set<Pokemon> pokemons = new HashSet<>();
        pokemons.add(savedPokemon1);
        pokemons.add(savedPokemon2);
        //créer une nouvelle attaque en lui assignant des pokemons
        attack.setPokemons(pokemons);
        Attack savedAttack = attackService.save(attack);

        return ResponseEntity.ok().build();
    }

    //assigner une attaque déjà existantee et rajouter des pokemons déjà existants à cette attaque
    @PostMapping("/assignAttackToPokemons/{AttackId}")
    public ResponseEntity<Attack> assignAttackToPokemons(@PathVariable Long AttackId, @RequestParam List<Long> PokemonIds) {
        //recuperer l'attaque par son id
        Optional<Attack> plannedAttack = attackService.findWithPokemonsById(AttackId);
        if(!plannedAttack.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Attack attack = plannedAttack.get();

        //récupérer les pokemons par leurs ids
        List<Pokemon> pokemons = pokemonService.findAllById(PokemonIds);
        if(pokemons.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        //ajouter les pokemons à l'attaque
        attackService.addPokemonsToAttack(attack, pokemons);
        return ResponseEntity.ok(attack);
    }

}
