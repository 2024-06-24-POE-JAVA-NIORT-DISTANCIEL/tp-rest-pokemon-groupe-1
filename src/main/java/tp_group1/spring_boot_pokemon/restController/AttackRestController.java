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
    @GetMapping("{/id}")
    public ResponseEntity<Attack> getAttackById(@PathVariable Long id) {
        Optional<Attack> attack = attackService.findById(id);
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
    @DeleteMapping("{/id}")
    public ResponseEntity<Void> deleteAttackById(@PathVariable Long id) {
        attackService.findById(id);
        if(attackService.findById(id).isPresent()) {
            attackService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
