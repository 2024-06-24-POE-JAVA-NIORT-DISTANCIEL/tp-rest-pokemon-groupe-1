package tp_group1.spring_boot_pokemon.model;

import jakarta.persistence.*;

@Entity
public class Fight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "POKEMON_A_ID")
    private Pokemon pokemonA;

    @ManyToOne
    @JoinColumn(name = "POKEMON_B_ID")
    private Pokemon pokemonB;

    private String result;
    private String winner;
    private String loser;
    private Integer experienceGained;
    private String log;

    public Fight() {
    }

    public Fight(Long id, Pokemon pokemonA, Pokemon pokemonB, String result, String winner, String loser, Integer experienceGained, String log) {
        this.id = id;
        this.pokemonA = pokemonA;
        this.pokemonB = pokemonB;
        this.result = result;
        this.winner = winner;
        this.loser = loser;
        this.experienceGained = experienceGained;
        this.log = log;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pokemon getPokemonA() {
        return pokemonA;
    }

    public void setPokemonA(Pokemon pokemonA) {
        this.pokemonA = pokemonA;
    }

    public Pokemon getPokemonB() {
        return pokemonB;
    }

    public void setPokemonB(Pokemon pokemonB) {
        this.pokemonB = pokemonB;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getLoser() {
        return loser;
    }

    public void setLoser(String loser) {
        this.loser = loser;
    }

    public Integer getExperienceGained() {
        return experienceGained;
    }

    public void setExperienceGained(Integer experienceGained) {
        this.experienceGained = experienceGained;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
