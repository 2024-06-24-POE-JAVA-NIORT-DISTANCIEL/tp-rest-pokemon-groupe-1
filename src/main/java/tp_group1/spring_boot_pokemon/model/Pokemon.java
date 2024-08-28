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
    @JoinColumn(name = "SPECIES_ID")
    private Species species;

    @ManyToOne
    @JoinColumn(name = "TRAINER_ID")
    private Trainer trainer;

    @OneToMany
    @JoinColumn(name = "ATTACK_ID")
    private Set<Attack> attacks;

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

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Set<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(Set<Attack> attacks) {
        this.attacks = attacks;
    }
}
