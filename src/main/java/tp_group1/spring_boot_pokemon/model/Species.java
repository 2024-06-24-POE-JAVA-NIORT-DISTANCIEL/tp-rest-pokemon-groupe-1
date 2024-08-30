package tp_group1.spring_boot_pokemon.model;


import jakarta.persistence.*;

@Entity
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private Integer initialHealthPoints;

    public Species() {
    }

    public Species(Long id, String name, String type, Integer initialHealthPoints) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.initialHealthPoints = initialHealthPoints;
    }

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

    public Integer getInitialHealthPoints() {
        return initialHealthPoints;
    }

    public void setInitialHealthPoints(Integer initialHealthPoints) {
        this.initialHealthPoints = initialHealthPoints;
    }
}
