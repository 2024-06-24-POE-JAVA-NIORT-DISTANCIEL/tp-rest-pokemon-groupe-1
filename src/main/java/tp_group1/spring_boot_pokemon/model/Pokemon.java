package tp_group1.spring_boot_pokemon.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer level = 1;
    private Integer experience = 0;
    private Integer healthPoints;
    private Integer maxHealthPoints;

    @ManyToOne
    @JoinColumn(name = "TRAINER_ID")
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "SPECIES_ID")
    private Species species;

    @ManyToMany(mappedBy = "pokemons")
    private Set<Attack> attacks = new HashSet<>();


    public Pokemon() {
    }

    public Pokemon(Long id, String name, Integer level, Integer experience, Integer healthPoints, Integer maxHealthPoints, Trainer trainer, Species species, Set<Attack> attacks) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.experience = experience;
        this.healthPoints = healthPoints;
        this.maxHealthPoints = maxHealthPoints;
        this.trainer = trainer;
        this.species = species;
        this.attacks = attacks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(Integer healthPoints) {
        this.healthPoints = healthPoints;
    }

    public Integer getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public void setMaxHealthPoints(Integer maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Set<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(Set<Attack> attacks) {
        this.attacks = attacks;
    }
}
