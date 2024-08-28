package tp_group1.spring_boot_pokemon.model;


import jakarta.persistence.*;

@Entity
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private int initialHealthPoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
