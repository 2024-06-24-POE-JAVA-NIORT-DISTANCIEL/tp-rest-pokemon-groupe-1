package tp_group1.spring_boot_pokemon.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp_group1.spring_boot_pokemon.dao.FightDao;
import tp_group1.spring_boot_pokemon.dao.PokemonDao;
import tp_group1.spring_boot_pokemon.model.Fight;
import tp_group1.spring_boot_pokemon.model.Pokemon;

import java.util.List;
import java.util.Optional;

@Service
public class FightService {
    @Autowired
    private FightDao fightDao;
    @Autowired
    private PokemonDao pokemonDao;

    //créer et sauvegarder un combat
    @Transactional
    public Fight save(Fight fight) {
        return fightDao.save(fight);
    }

    //trouver un combat par id
    public Optional<Fight> findById(Long id) {
        return fightDao.findById(id);
    }

    //trouver tous les combats
    public List<Fight> findAll() {
        return fightDao.findAll();
    }

    //supprimer un combat par son id
    @Transactional
    public void deleteById(Long id) {
        fightDao.deleteById(id);
    }


}
