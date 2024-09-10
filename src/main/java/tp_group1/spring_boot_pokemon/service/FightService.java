package tp_group1.spring_boot_pokemon.service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp_group1.spring_boot_pokemon.dao.FightDao;
import tp_group1.spring_boot_pokemon.dao.PokemonDao;
import tp_group1.spring_boot_pokemon.dto.PokemonDto;
import tp_group1.spring_boot_pokemon.model.Fight;
import tp_group1.spring_boot_pokemon.model.Pokemon;
import tp_group1.spring_boot_pokemon.model.Trainer;

import java.util.List;
import java.util.Optional;

import static tp_group1.spring_boot_pokemon.model.AttackType.AIR;

@Service
public class FightService {
    @Autowired
    private FightDao fightDao;
    @Autowired
    private PokemonService pokemonService;
    @Autowired
    private PokemonDao pokemonDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(FightService.class);

    //créer un combat
    @Transactional
    public Fight startFight(Long pokemonId1, Long pokemonId2) {
        LOGGER.info("Début du combat entre les Pokémon avec ID: {} et {}", pokemonId1, pokemonId2);

        // Vérifier si les pokemons existent avec leur ID
        PokemonDto savedPokemon1 = pokemonService.findById(pokemonId1);
        PokemonDto savedPokemon2 = pokemonService.findById(pokemonId2);

        if (savedPokemon1 == null || savedPokemon2 == null) {
            LOGGER.error("L'un des deux pokémons n'existe pas ! ID1: {}, ID2: {}", pokemonId1, pokemonId2);
            throw new IllegalArgumentException("L'un des deux pokemons n'existe pas !");
        }

        // Conversion des DTOs en entités
        Pokemon pokemon1 = pokemonService.dtoToEntity(savedPokemon1);
        Pokemon pokemon2 = pokemonService.dtoToEntity(savedPokemon2);

        // Vérifier si ces pokemons n'ont pas les points de vie = 0
        if (pokemon1.getHealthPoints() <= 0 || pokemon2.getHealthPoints() <= 0) {
            LOGGER.error("L'un de ces pokémons n'a plus de points de vie ! {}: {} HP, {}: {} HP",
                    pokemon1.getName(), pokemon1.getHealthPoints(),
                    pokemon2.getName(), pokemon2.getHealthPoints());
            throw new IllegalArgumentException("L'un de ces pokémons n'a plus de points de vie !");
        }

        // Initialiser les points de vie actuels à leur valeur maximale
        pokemon1.setHealthPoints(Math.min(pokemon1.getHealthPoints(), pokemon1.getMaxHealthPoints()));
        pokemon2.setHealthPoints(Math.min(pokemon2.getHealthPoints(), pokemon2.getMaxHealthPoints()));

        LOGGER.info("Points de vie actuels définis: {} (HP: {}), {} (HP: {})",
                pokemon1.getName(), pokemon1.getHealthPoints(),
                pokemon2.getName(), pokemon2.getHealthPoints());

        //initialiser le combat
        Fight fight = new Fight();
        fight.setPokemonA(pokemon1);
        fight.setPokemonB(pokemon2);
        LOGGER.info("le Pokemon {} possède un type d'attaque {} et le Pokemon {} un type d'attaque {}",
                pokemon1.getName(), pokemon1.getSpecies().getAttack().getAttackType(),
                pokemon2.getName(), pokemon2.getSpecies().getAttack().getAttackType());

        //choix aléatoire du premier attaquant entre les deux pokemons
        Pokemon attacker = Math.random() < 0.5 ? pokemon1 : pokemon2;
        Pokemon defender = attacker == pokemon1 ? pokemon2 : pokemon1;

        LOGGER.info("{} commence l'attaque contre {}", attacker.getName(), defender.getName());
        //boucle du combat
        while(pokemon1.getHealthPoints() >= 0 && pokemon2.getHealthPoints() >=0) {
            // Calcul des dégâts infligés : (niveau du pokemon / 10) * nombre de points de dégâts de l’attaque * modificateur d’attaque
            double damageFight = calculateDamageMultiplier(attacker.getSpecies().getSpecieType(), defender.getSpecies().getSpecieType());
            int baseDamage = attacker.getSpecies().getAttack().getDamage();
            double levelFactor = attacker.getLevel() / 10.0;
            int damage = (int) (levelFactor * baseDamage * damageFight);
            LOGGER.info("{} attaque {} et inflige {} points de dégâts",
                    attacker.getName(),
                    defender.getName(),
                    damage);

            //pour mettre à 0 et ne pas avoir de points négatifs
            defender.setHealthPoints(Math.max(defender.getHealthPoints() - damage, 0));
            LOGGER.info("{} a maintenant {} HP", defender.getName(), defender.getHealthPoints());

            // Vérification du vainqueur
            if (defender.getHealthPoints() <= 0) {
                LOGGER.info("{} a gagné le combat contre {}", attacker.getName(), defender.getName());
                fight.setWinner(attacker.getName());
                fight.setLoser(defender.getName());
                fight.setResult(attacker.getName() + " a gagné le combat !");
                break;
            }

            // Inverser les rôles pour le prochain tour
            Pokemon temp = attacker;
            attacker = defender;
            defender = temp;
            LOGGER.info("Maintenant, {} attaque {}", attacker.getName(), defender.getName());
        }

        // Gestion des points gagnés par le vainqueur
        //Un Pokémon gagnant un combat remporte le nombre de points d’expérience suivant : niveau du
        //Pokémon perdant * 4. Tous les 5 points d’expérience, un Pokémon gagne un niveau. A chaque
        //niveau, un Pokémon gagne 10 points de vie. Il faut alors afficher le gain d'expérience, voire le
        //passage de niveau. Le dresseur du Pokémon gagne 100 unités de monnaie * le niveau du Pokémon
        //défait
        int experienceGain = defender.getLevel() * 4;
        attacker.setExperience(attacker.getExperience() + experienceGain);
        LOGGER.info("{} a gagné {} d'expérience", attacker.getName(),experienceGain);
        LOGGER.info("{} a maintenant {} points d'expérience", attacker.getName(), attacker.getExperience());
        // Monter de niveau pour le pokemon
        while(attacker.getExperience() >=5) {
            attacker.setExperience(attacker.getExperience() - 5);
            attacker.setLevel(attacker.getLevel() + 1);
            attacker.setHealthPoints(attacker.getHealthPoints() + 10);
            attacker.setMaxHealthPoints(attacker.getMaxHealthPoints() + 10);
            LOGGER.info("{} est monté au niveau {} et a {} de points de vie",
                    attacker.getName(),
                    attacker.getLevel(),
                    attacker.getHealthPoints());
        }
        pokemonDao.save(attacker);
        // Gain pour le dresseur
        Trainer attackerTrainer = attacker.getTrainer();
        if(attackerTrainer != null) {
            int rewardTrainer = defender.getLevel() * 100;
            attackerTrainer.setWallet(attackerTrainer.getWallet() + rewardTrainer);
            LOGGER.info("{} du Pokemon {} a gagné {} unités",
                    attackerTrainer.getUsername(),
                    attacker.getName(),
                    attackerTrainer.getWallet());
        }

        // Sauvegarder le combat
        return fightDao.save(fight);
    }

    // Méthode pour calculer le modificateur de dégâts en fonction des types
    public static double calculateDamageMultiplier(String typeAttaque, String typeDefenseur) {
        double damageFight = 1.0;
        switch (typeAttaque) {
            case "AIR":
                switch (typeDefenseur) {
                    case "AIR":
                        damageFight = 1.0;
                        break;
                    case "EAU":
                        damageFight = 0.7;
                        break;
                    case "INSECTE":
                        damageFight = 1.0;
                        break;
                    case "PLANTE":
                        damageFight = 1.3;
                        break;
                    default:
                        System.out.println("Type défensif inconnu.");
                }
                break;

            case "EAU":
                switch (typeDefenseur) {
                    case "AIR":
                        damageFight = 1.3;
                        break;
                    case "EAU":
                        damageFight = 1.0;
                        break;
                    case "INSECTE":
                        damageFight = 0.7;
                        break;
                    case "PLANTE":
                        damageFight = 1.0;
                        break;
                    default:
                        System.out.println("Type défensif inconnu.");
                }
                break;

            case "INSECTE":
                switch (typeDefenseur) {
                    case "AIR":
                        damageFight = 1.0;
                        break;
                    case "EAU":
                        damageFight = 1.3;
                        break;
                    case "INSECTE":
                        damageFight = 1.0;
                        break;
                    case "PLANTE":
                        damageFight = 0.7;
                        break;
                    default:
                        System.out.println("Type défensif inconnu.");
                }
                break;

            case "PLANTE":
                switch (typeDefenseur) {
                    case "AIR":
                        damageFight = 0.7;
                        break;
                    case "EAU":
                        damageFight = 1.0;
                        break;
                    case "INSECTE":
                        damageFight = 1.3;
                        break;
                    case "PLANTE":
                        damageFight = 1.0;
                        break;
                    default:
                        System.out.println("Type défensif inconnu.");
                }
                break;
            default:
                System.out.println("Type d'attaque inconnu.");
        }

        return damageFight;
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
