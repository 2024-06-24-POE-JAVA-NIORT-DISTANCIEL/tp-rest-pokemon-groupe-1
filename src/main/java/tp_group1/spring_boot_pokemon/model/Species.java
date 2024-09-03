package tp_group1.spring_boot_pokemon.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String specieName;
    private String specieType;
    private Integer initialHealthPoints;

    @ManyToOne
    @JoinColumn(name = "ATTACK_ID")
    private Attack attack;

    @OneToMany(mappedBy = "species")
    private Set<Pokemon> pokemons = new HashSet<>();

    public Species() {
    }

    public Species(Long id, String specieName, String specieType, Integer initialHealthPoints, Attack attack, Set<Pokemon> pokemons) {
        this.id = id;
        this.specieName = specieName;
        this.specieType = specieType;
        this.initialHealthPoints = initialHealthPoints;
        this.attack = attack;
        this.pokemons = pokemons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecieName() {
        return specieName;
    }

    public void setSpecieName(String specieName) {
        this.specieName = specieName;
    }

    public String getSpecieType() {
        return specieType;
    }

    public void setSpecieType(String specieType) {
        this.specieType = specieType;
    }

    public Integer getInitialHealthPoints() {
        return initialHealthPoints;
    }

    public void setInitialHealthPoints(Integer initialHealthPoints) {
        this.initialHealthPoints = initialHealthPoints;
    }

    public Attack getAttack() {
        return attack;
    }

    public void setAttack(Attack attack) {
        this.attack = attack;
    }

    public Set<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(Set<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    // For logging purposes
    @Override
    public String toString() {
        return "Species{" +
                "id=" + id +
                ", specieName='" + specieName + '\'' +
                ", specieType='" + specieType + '\'' +
                ", initialHealthPoints=" + initialHealthPoints +
                ", attacks=" + attack +
                ", pokemons=" + pokemons +
                '}';
    }
}
