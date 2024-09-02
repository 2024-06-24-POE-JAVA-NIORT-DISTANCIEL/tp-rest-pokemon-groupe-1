package tp_group1.spring_boot_pokemon.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp_group1.spring_boot_pokemon.model.Fight;
import tp_group1.spring_boot_pokemon.service.FightService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fightinprogress")
public class FightRestController {
    @Autowired
    private FightService fightService;


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

    //POST - créer un nouveau combat
    @PostMapping
    public Fight save(@RequestBody Fight fight) {
        return fightService.save(fight);
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

}
