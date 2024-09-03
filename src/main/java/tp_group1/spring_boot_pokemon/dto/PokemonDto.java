package tp_group1.spring_boot_pokemon.dto;

import jakarta.persistence.*;
import tp_group1.spring_boot_pokemon.model.Species;
import tp_group1.spring_boot_pokemon.model.Attack;
import tp_group1.spring_boot_pokemon.model.Trainer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PokemonDto {
   
    private Long id;

    private String name;
    private Integer level = 1;
    private Integer experience = 0;

    private Long speciesId;

    private Integer maxHealthPoints;
    private Integer healthPoints;

    private Long trainerId;

    private Set<Attack> attacks = new HashSet<>();


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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Long getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(Long speciesId) {
        this.speciesId = speciesId;
    }

    public Integer getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public void setMaxHealthPoints(Integer maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }

    public Integer getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(Integer healthPoints) {
        this.healthPoints = healthPoints;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    public Set<Attack> getAttacks() {return attacks;}

    public void setAttacks(Set<Attack> attacks) {
        this.attacks = attacks;
    }
}
