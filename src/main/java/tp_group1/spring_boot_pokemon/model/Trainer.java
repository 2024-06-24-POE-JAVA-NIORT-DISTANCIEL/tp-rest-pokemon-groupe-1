package tp_group1.spring_boot_pokemon.model;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

@Entity
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private int wallet;



    // plusieurs dresseurs peuvent avoir le même pokemon
    @OneToMany(mappedBy = "trainer", fetch = FetchType.EAGER)
    private Set<Pokemon> pokemons = new HashSet<>();

    @OneToMany(mappedBy = "trainer")
    private Set<Inventory> inventories = new HashSet<>();



    // Constructeurs
    public Trainer() {
    }

    public Trainer(Long id, String username, String password, int wallet, Set<Pokemon> pokemons, Set<Inventory> inventories) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.wallet = wallet;
        this.pokemons = pokemons;
        this.inventories = inventories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }


    public Set<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(Set<Inventory> inventories) {
        this.inventories = inventories;
    }

    public Set<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(Set<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
}
