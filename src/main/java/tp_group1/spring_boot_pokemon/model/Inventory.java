package tp_group1.spring_boot_pokemon.model;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;


@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @OneToMany (mappedBy = "Inventory")
    private Set<Item> items = new HashSet<>();

    @OneToMany (mappedBy = "Inventory")
    private Set<Trainer> trainers = new HashSet<>();

    // Constructeurs
    public Inventory() {
    }

    public Inventory(Long id, int quantity, Set<Item> items, Set<Trainer> trainers) {
        this.id = id;
        this.quantity = quantity;
        this.items = items;
        this.trainers = trainers;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Set<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(Set<Trainer> trainers) {
        this.trainers = trainers;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", items=" + items +
                ", trainers=" + trainers +
                '}';
    }
}
