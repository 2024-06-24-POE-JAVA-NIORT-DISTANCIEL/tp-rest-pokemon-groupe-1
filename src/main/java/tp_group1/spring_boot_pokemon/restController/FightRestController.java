package tp_group1.spring_boot_pokemon.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp_group1.spring_boot_pokemon.dto.PokemonDto;
import tp_group1.spring_boot_pokemon.model.Fight;
import tp_group1.spring_boot_pokemon.service.FightService;
import tp_group1.spring_boot_pokemon.service.PokemonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fightinprogress")
public class FightRestController {
    @Autowired
    private FightService fightService;
    @Autowired
    private PokemonService pokemonService;


    //GET - trouver un combat par id
    @GetMapping("/{id}")
    public ResponseEntity<Fight> getFightById(@PathVariable Long id) {
        Optional<Fight> fight = fightService.findById(id);
        return fight.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //GET ALL - trouver tous les combats
    @GetMapping
    public List<Fight> getAllFights() {
        return fightService.findAll();
    }

    //DELETE BY ID - supprimer une attaque uniquement s'il n'y a plus de pokemon associé
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFightById(@PathVariable Long id) {
        //trouver le combat par son id
        Optional<Fight> progressedFight = fightService.findById(id);
        if(!progressedFight.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Fight fight = progressedFight.get();
        //verifier s'il y a un ou plusieurs pokemons associés au combat
        if(fight.getPokemonA() != null || fight.getPokemonB() != null) {
            return ResponseEntity.badRequest().build();
        }
        //supprimer le combat par son id
        fightService.deleteById(id);
        return ResponseEntity.ok().build();

    }

    //POST - commence le combat
    @PostMapping("/start")
    public ResponseEntity<Fight> startFight(@RequestBody Fight fight) {
        // Vérifiez que les IDs des Pokémon sont bien présents dans la requête
        if (fight.getPokemonA() == null || fight.getPokemonB() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        // Assurez-vous que les IDs des Pokémon sont récupérés correctement
        Long pokemonId1 = fight.getPokemonA().getId();
        Long pokemonId2 = fight.getPokemonB().getId();

        // Vérifiez les Pokémon avec leurs IDs
        PokemonDto savedPokemon1 = pokemonService.findById(pokemonId1);
        PokemonDto savedPokemon2 = pokemonService.findById(pokemonId2);

        if (savedPokemon1 == null || savedPokemon2 == null) {
            return ResponseEntity.badRequest().body(null);
        }

        // Démarrer le combat
        Fight actualFight = fightService.startFight(savedPokemon1.getId(), savedPokemon2.getId());

        // Retourner le résultat du combat
        return ResponseEntity.ok(actualFight);
    }

}
