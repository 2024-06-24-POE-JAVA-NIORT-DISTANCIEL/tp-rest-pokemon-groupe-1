package tp_group1.spring_boot_pokemon.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp_group1.spring_boot_pokemon.service.TrainerService;

@RestController
@RequestMapping("/trainers")
public class TrainerRestController {
    @Autowired
    private TrainerService trainerService;
}
