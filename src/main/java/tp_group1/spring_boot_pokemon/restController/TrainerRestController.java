package tp_group1.spring_boot_pokemon.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp_group1.spring_boot_pokemon.model.Trainer;
import tp_group1.spring_boot_pokemon.service.TrainerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trainers")
public class TrainerRestController {
    @Autowired
    private TrainerService trainerService;

    //GET
    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable Long id) {
        Optional<Trainer> trainer = trainerService.findById(id);
        return trainer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //GET ALL
    @GetMapping
    public List<Trainer> getAllTrainers() {
        return trainerService.findAll();
    }

    //POST
    @PostMapping
    public Trainer save(@RequestBody Trainer trainer) {
        return trainerService.save(trainer);
    }

    //DELETE BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainerById(@PathVariable Long id) {
        trainerService.findById(id);
        if(trainerService.findById(id).isPresent()){
            trainerService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
