package tp_group1.spring_boot_pokemon.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import tp_group1.spring_boot_pokemon.model.Inventory;
import tp_group1.spring_boot_pokemon.model.Pokemon;

import java.util.HashSet;
import java.util.Set;

public class TrainerDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private Integer wallet = 100;

    private Set<Pokemon> pokemons = new HashSet<>();

    private Set<Inventory> inventories = new HashSet<>();

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

    public Integer getWallet() {
        return wallet;
    }

    public void setWallet(Integer wallet) {
        this.wallet = wallet;
    }

    public Set<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(Set<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public Set<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(Set<Inventory> inventories) {
        this.inventories = inventories;
    }
}
