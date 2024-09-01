package tp_group1.spring_boot_pokemon.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer level = 1;
    private Integer experience = 0;

    @ManyToOne
    @JoinColumn(name = "SPECIE_ID")
    private Species species;

    private Integer maxHealthPoints;
    private Integer healthPoints;

    @ManyToMany(mappedBy = "pokemons", cascade = CascadeType.ALL)
    private Set<Attack> attacks = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "TRAINER_ID")
    private Trainer trainer;


    public Pokemon() {
    }

    public Pokemon(Long id, String name, Integer level, Integer experience, Integer maxHealthPoints, Integer healthPoints, Trainer trainer, Species species, Set<Attack> attacks) {
        this.id = id;
        this.name = name;
        this.level = (level != null) ? level : 1;
        this.experience = (experience != null) ? level : 0;
        this.species = species;
        this.attacks = attacks;
        this.trainer = trainer;
        initializeHealthPoints();
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
        if(level != null && level >=1) {
            this.level = level;
        }
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        if(experience != null && experience >= 0) {
            this.experience = experience;
        }
    }

    public Integer getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(Integer healthPoints) {
        if (healthPoints != null && healthPoints >= 0 && healthPoints <= this.maxHealthPoints) {
            this.healthPoints = healthPoints;
        } else {
            throw new IllegalArgumentException("Health points must be non-negative and less than or equal to max health points.");
        }
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
        initializeHealthPoints();
    }

    public Set<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(Set<Attack> attacks) {
        this.attacks = attacks;
    }

    @PostLoad
    @PostPersist
    private void initializeHealthPoints() {
        if (this.species != null) {
            this.maxHealthPoints = this.species.getInitialHealthPoints();
            this.healthPoints = this.maxHealthPoints;
        }
    }
}
