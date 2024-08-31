package tp_group1.spring_boot_pokemon.restController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import tp_group1.spring_boot_pokemon.model.Species;
import tp_group1.spring_boot_pokemon.service.SpeciesService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/species")
public class SpeciesRestController {

    @Autowired
    private SpeciesService speciesService;

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Species> getSpeciesById(@PathVariable Long id) {
        Optional<Species> species = speciesService.findById(id);
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpeciesById(@PathVariable Long id) {
        try {
            speciesService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
