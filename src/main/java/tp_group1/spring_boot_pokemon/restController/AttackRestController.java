package tp_group1.spring_boot_pokemon.restController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import tp_group1.spring_boot_pokemon.model.Attack;
import tp_group1.spring_boot_pokemon.service.AttackService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/attacks")
public class AttackRestController {
    @Autowired
    private AttackService attackService;

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
    public void createAttackForPokemons(@RequestBody Attack attack) {

    }

    //assigner une attaque déjà existantee et rajouter des pokemons déjà existants à cette attaque
    @PostMapping("/assignAttackToPokemons/{AttackId}/{PokemonId}")
    public void assigneAttackToPokemons(@PathVariable Long AttackId, @PathVariable Long PokemonId) {

    }

}
