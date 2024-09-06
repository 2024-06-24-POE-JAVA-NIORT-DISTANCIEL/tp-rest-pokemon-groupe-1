package tp_group1.spring_boot_pokemon.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Attack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String attackName;

    @Enumerated(EnumType.STRING)
    private AttackType attackType;

    private Integer damage;

    @ManyToMany
    @JoinTable(
            name = "pokemon_attack",
            joinColumns = @JoinColumn(name = "attack_id"),
            inverseJoinColumns = @JoinColumn(name = "pokemon_id")
    )
    private Set<Pokemon> pokemons = new HashSet<>();

    @OneToMany(mappedBy = "attack")
    private Set<Species> species= new HashSet<>();


    public Attack() {
    }

    public Attack(Long id, String attackName, AttackType attackType, Integer damage, Set<Pokemon> pokemons, Set<Species> species) {
        this.id = id;
        this.attackName = attackName;
        this.attackType = attackType;
        this.damage = damage;
        this.pokemons = pokemons;
        this.species = species;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttackName() {
        return attackName;
    }

    public void setAttackName(String attackName) {
        this.attackName = attackName;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Set<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(Set<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public Set<Species> getSpecies() {
        return species;
    }

    public void setSpecies(Set<Species> species) {
        this.species = species;
    }
}
