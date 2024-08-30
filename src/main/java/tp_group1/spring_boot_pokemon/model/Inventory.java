package tp_group1.spring_boot_pokemon.model;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;


@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "TRAINER_ID")
    private Trainer trainer;

    // Constructeurs
    public Inventory() {
    }




    public Inventory(Long id, Integer quantity, Item item, Trainer trainer) {
        this.id = id;
        this.quantity = quantity;
        this.item = item;
        this.trainer = trainer;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", items=" + item +
                ", trainers=" + trainer +
                '}';
    }
}
