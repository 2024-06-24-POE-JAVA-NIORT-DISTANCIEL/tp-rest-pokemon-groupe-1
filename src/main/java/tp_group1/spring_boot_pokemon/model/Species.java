package tp_group1.spring_boot_pokemon.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String name;
    private String type;
    private int initialHealthPoints;


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getInitialHealthPoints() {
        return initialHealthPoints;
    }

    public void setInitialHealthPoints(int initialHealthPoints) {
        this.initialHealthPoints = initialHealthPoints;
    }
}
