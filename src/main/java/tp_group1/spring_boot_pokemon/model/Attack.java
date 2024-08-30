package tp_group1.spring_boot_pokemon.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Attack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String attackName;
    private String type;
    private int damage;

    @ManyToMany
    @JoinTable(
            name = "pokemon_attack",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "attack_id")
    )
    private Set<Pokemon> pokemons;

    public Attack() {
    }

    public Attack(Long id, String attackName, String type, int damage) {
        this.id = id;
        this.attackName = attackName;
        this.type = type;
        this.damage = damage;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
