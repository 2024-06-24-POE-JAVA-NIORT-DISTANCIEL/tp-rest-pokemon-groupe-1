package tp_group1.spring_boot_pokemon.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp_group1.spring_boot_pokemon.dao.AttackDao;
import tp_group1.spring_boot_pokemon.model.Attack;
import tp_group1.spring_boot_pokemon.dto.PokemonDto;
import tp_group1.spring_boot_pokemon.model.Pokemon;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AttackService {
    @Autowired
    private AttackDao attackDao;

    @Autowired
    private PokemonService pokemonService;

    @Transactional
    public Attack save(Attack attack) {
        return attackDao.save(attack);
    }

    public Optional<Attack> findById(Long AttackId) {
        return attackDao.findById(AttackId);
    }

    public List<Attack> findAll() {
        return attackDao.findAll();
    }

    @Transactional
    public void deleteById(Long AttackId) {
        attackDao.deleteById(AttackId);
    }

    public Attack update(Long AttackId, Attack newAttackData) {
        return attackDao.findById(AttackId)
                .map(existingAttack -> {
                    existingAttack.setAttackName(newAttackData.getAttackName());
                    existingAttack.setAttackType(newAttackData.getAttackType());
                    existingAttack.setDamage(newAttackData.getDamage());
                    existingAttack.setPokemons(newAttackData.getPokemons());
                    existingAttack.setSpecies(newAttackData.getSpecies());
                    return attackDao.save(existingAttack);
                }).orElseThrow(() -> new RuntimeException("Attack not found"));
    }

    @Transactional
    public void addPokemonsToAttack(Attack attack, List<PokemonDto> pokemonDtos) {
        Set<Pokemon> savedPokemons = new HashSet<>(attack.getPokemons());
        List<Pokemon> pokemons = pokemonDtos.stream()
                .map(pokemonService::dtoToEntity)
                .toList();
        savedPokemons.addAll(pokemons);
        attack.setPokemons(savedPokemons);
        save(attack);

        for (Pokemon pokemon : pokemons) {
            Set<Attack> attacks = new HashSet<>(pokemon.getAttacks());
            attacks.add(attack);
            pokemon.setAttacks(attacks);
            pokemonService.save(pokemonService.entityToDto(pokemon));
        }
    }

    public Optional<Attack> findWithPokemonsById(Long AttackId) {
        return attackDao.findById(AttackId);
    }
}
