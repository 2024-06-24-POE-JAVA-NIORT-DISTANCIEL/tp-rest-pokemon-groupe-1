package tp_group1.spring_boot_pokemon.restController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import tp_group1.spring_boot_pokemon.model.Species;
import tp_group1.spring_boot_pokemon.service.SpeciesService;
import tp_group1.spring_boot_pokemon.service.AttackService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/species")
public class SpeciesRestController {

    @Autowired
    private SpeciesService speciesService;

    @Autowired
    private AttackService attackService;


    // GET by ID
    @GetMapping("/{SpecieId}")
    public ResponseEntity<Species> getSpeciesById(@PathVariable Long SpecieId) {
        Optional<Species> species = speciesService.findById(SpecieId);
        return species.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET all
    @GetMapping
    public List<Species> getAllSpecies() {
        return speciesService.findAllSpecies();
    }

    // POST to create a species
    @PostMapping
    public Species save(@RequestBody Species species) {
        return speciesService.save(species);
    }

    // DELETE by ID
    @DeleteMapping("/{SpecieId}")
    public ResponseEntity<Void> deleteSpeciesById(@PathVariable Long SpecieId) {
        Optional<Species> savedSpecies = speciesService.findById(SpecieId);
        if(!savedSpecies.isPresent()) {
            speciesService.deleteById(SpecieId);
            return ResponseEntity.notFound().build();
        }
        Species species = savedSpecies.get();
        if(!species.getPokemons().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        speciesService.deleteById(SpecieId);
        return ResponseEntity.ok().build();
    }
}
