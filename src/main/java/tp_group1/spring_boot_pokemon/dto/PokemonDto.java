package tp_group1.spring_boot_pokemon.dto;

import tp_group1.spring_boot_pokemon.model.Species;
import tp_group1.spring_boot_pokemon.model.Attack;
import tp_group1.spring_boot_pokemon.model.Trainer;

public class PokemonDto {
    // Pokemon attributes
    private Long id;
    private String name;
    private Integer level;
    private Integer experience;
    private Integer healthPoints;
    private Integer maxHealthPoints;

    // Pokemon relationships
    private Species species;
    private Attack attack;
    private Trainer trainer;
}
