package tp_group1.spring_boot_pokemon.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.Set;
import java.util.HashSet;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private Integer wallet = 100; // Valeur par défaut si aucun constructeur n'est utilisé

    @OneToMany(mappedBy = "trainer", fetch = FetchType.EAGER)
    private Set<Pokemon> pokemons = new HashSet<>();

    @OneToMany(mappedBy = "trainer")
    private Set<Inventory> inventories = new HashSet<>();

    // Constructeurs
    public Trainer() {
        // Ce constructeur vide utilise la valeur par défaut définie dans la déclaration du champ.
    }

    public Trainer(Long id, String username, String password, Integer wallet, Set<Pokemon> pokemons, Set<Inventory> inventories) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.wallet = (wallet != null) ? wallet : 100; // Valeur par défaut de 100 lors de la création
        this.pokemons = pokemons;
        this.inventories = inventories;
    }

    // Getters et Setters
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

    public Integer getWallet() {
        return wallet;
    }

    public void setWallet(Integer wallet) {
        if (wallet != null && wallet >= 0) {
            this.wallet = wallet;
        }
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
