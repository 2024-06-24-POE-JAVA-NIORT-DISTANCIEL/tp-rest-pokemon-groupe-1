package tp_group1.spring_boot_pokemon.restController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import tp_group1.spring_boot_pokemon.model.Attack;
import tp_group1.spring_boot_pokemon.dto.PokemonDto;
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

    @GetMapping("/{AttackId}")
    public ResponseEntity<Attack> getAttackById(@PathVariable Long AttackId) {
        Optional<Attack> attack = attackService.findById(AttackId);
        return attack.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Attack> getAllAttacks() {
        return attackService.findAll();
    }

    @PostMapping
    public Attack save(@RequestBody Attack attack) {
        return attackService.save(attack);
    }

    @DeleteMapping("/{AttackId}")
    public ResponseEntity<Void> deleteAttackById(@PathVariable Long AttackId) {
        Optional<Attack> savedAttack = attackService.findById(AttackId);
        if (!savedAttack.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Attack attack = savedAttack.get();
        if (!attack.getPokemons().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        attackService.deleteById(AttackId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{AttackId}")
    public ResponseEntity<Attack> updateAttack(@PathVariable Long AttackId, @RequestBody Attack attack) {
        try {
            Attack updatedAttack = attackService.update(AttackId, attack);
            return ResponseEntity.ok(updatedAttack);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/createAttackForPokemons")
    public ResponseEntity<Attack> createAttackForPokemons(@RequestParam Long PokemonId1, @RequestBody Attack attack) {
        Optional<PokemonDto> pokemonDto = Optional.ofNullable(pokemonService.findById(PokemonId1));
        if (pokemonDto.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        Pokemon savedPokemon = pokemonService.dtoToEntity(pokemonDto.get());
        Set<Pokemon> pokemons = new HashSet<>();
        pokemons.add(savedPokemon);
        attack.setPokemons(pokemons);
        Attack savedAttack = attackService.save(attack);

        return ResponseEntity.ok(savedAttack);
    }

    @PostMapping("/assignAttackToPokemons/{AttackId}")
    public ResponseEntity<Attack> assignAttackToPokemons(@PathVariable Long AttackId, @RequestParam List<Long> PokemonIds) {
        Optional<Attack> plannedAttack = attackService.findWithPokemonsById(AttackId);
        if (!plannedAttack.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Attack attack = plannedAttack.get();
        List<PokemonDto> pokemonDtos = pokemonService.findAllById(PokemonIds).stream()
                .toList();
        if (pokemonDtos.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        attackService.addPokemonsToAttack(attack, pokemonDtos);
        return ResponseEntity.ok(attack);
    }
}

