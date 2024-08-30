package tp_group1.spring_boot_pokemon.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private Integer initialHealthPoints;

    @OneToMany(mappedBy = "species")
    private Set<Attack> attacks = new HashSet<>();


    public Species() {
    }

    public Species(Long id, String name, String type, Integer initialHealthPoints, Set<Attack> attacks) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.initialHealthPoints = initialHealthPoints;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
