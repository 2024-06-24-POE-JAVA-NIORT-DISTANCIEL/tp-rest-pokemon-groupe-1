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
    @ManyToOne
    @JoinColumn(name = "POKEMON_ID")
    private Pokemon pokemon;



    // Constructeurs
    public Trainer() {
    }
    // Constructeur avec id (a mettre au fur et a mesure des test : , Set<Item> items, Map<Item, Integer> itemQuantities
    public Trainer(Long id, String username, String password, int wallet, Pokemon pokemon) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.wallet = wallet;
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

//    public Set<Item> getItems() {
//        return items;
//    }
//
//    public void setItems(Set<Item> items) {
//        this.items = items;
//    }
//
//    public Map<Item, Integer> getItemQuantities() {
//        return itemQuantities;
//    }
//
//    public void setItemQuantities(Map<Item, Integer> itemQuantities) {
//        this.itemQuantities = itemQuantities;
//    }
//
    public Pokemon getPokemon() { return pokemon; }

    public void setPokemon(Pokemon pokemon) { this.pokemon = pokemon; }
}
