package tp_group1.spring_boot_pokemon.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity

public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String specieName;
    private String specieType;
    private Integer initialHealthPoints;

    @OneToMany(mappedBy = "species")
    private Set<Attack> attacks = new HashSet<>();

    @OneToMany(mappedBy = "species")
    private Set<Pokemon> pokemons = new HashSet<>();

    public Species() {
    }

    public Species(Long id, String specieName, String specieType, Integer initialHealthPoints, Set<Attack> attacks, Set<Pokemon> pokemons) {
        this.id = id;
        this.specieName = specieName;
        this.specieType = specieType;
        this.initialHealthPoints = initialHealthPoints;
        this.attacks = attacks;
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

    public Set<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(Set<Attack> attacks) {
        this.attacks = attacks;
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
                ", attacks=" + attacks +
                ", pokemons=" + pokemons +
                '}';
    }
}
