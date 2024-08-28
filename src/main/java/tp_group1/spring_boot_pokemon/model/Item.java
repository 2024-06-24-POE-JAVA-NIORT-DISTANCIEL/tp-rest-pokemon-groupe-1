package tp_group1.spring_boot_pokemon.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Item {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private int cost;
    private String type;

//    @ManyToMany
//    @JoinColumn(name = "TRAINER_ID")
//    private Set<Trainer> trainers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public Set<Trainer> getTrainers() {
//        return trainers;
//    }
//
//    public void setTrainers(Set<Trainer> trainers) {
//        this.trainers = trainers;
//    }
}
