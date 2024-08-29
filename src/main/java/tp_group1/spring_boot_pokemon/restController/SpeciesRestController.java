package tp_group1.spring_boot_pokemon.restController;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import tp_group1.spring_boot_pokemon.service.SpeciesService;

@RestController
@RequestMapping("/species")
public class SpeciesRestController {
    @Autowired
    private SpeciesService speciesService;
}
