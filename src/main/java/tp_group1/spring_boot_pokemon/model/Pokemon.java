package tp_group1.spring_boot_pokemon.model;


import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int level;
    private int experience;
    private int healthPoints;
    private int maxHealthPoints;

    @ManyToOne
    @JoinColumn(name = "TRAINER_ID")
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "SPECIES_ID")
    private Species species;

    @ManyToMany
    @JoinTable(
            name = "pokemon_attack",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "attack_id")
    )
    private Set<Attack> attacks;


    public Pokemon() {
    }

    public Pokemon(Long id, String name, int level, int experience, int healthPoints, int maxHealthPoints, Trainer trainer) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.experience = experience;
        this.healthPoints = healthPoints;
        this.maxHealthPoints = maxHealthPoints;
        this.trainer = trainer;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public void setMaxHealthPoints(int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
}
