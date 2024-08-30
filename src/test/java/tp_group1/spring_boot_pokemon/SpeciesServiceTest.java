package tp_group1.spring_boot_pokemon;

import org.antlr.v4.runtime.misc.LogManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tp_group1.spring_boot_pokemon.model.Species;
import tp_group1.spring_boot_pokemon.service.SpeciesService;
import tp_group1.spring_boot_pokemon.model.Pokemon;
import tp_group1.spring_boot_pokemon.service.PokemonService;
import tp_group1.spring_boot_pokemon.model.Attack;
import tp_group1.spring_boot_pokemon.service.AttackService;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SpeciesServiceTest {

    @Autowired
    private SpeciesService speciesService;

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private AttackService attackService;

    @Test
    public void testSaveSpecies() {
        // Créer une nouvelle espèce
        Species species = new Species(null, "Pikachu", "Electric", 100, null, null);
        Species savedSpecies = speciesService.save(species);

        // Vérifier que l'ID est généré et n'est pas null
        assertNotNull(savedSpecies.getId());
        assertTrue(savedSpecies.getId() > 0);

        // Vérifier que l'espèce sauvegardée a les attributs corrects
        assertEquals("Pikachu", savedSpecies.getName());
        assertEquals("Electric", savedSpecies.getType());
        assertEquals(Integer.valueOf(100), savedSpecies.getInitialHealthPoints());
    }

    @Test
    public void testFindSpeciesById() {
        // Sauvegarder une nouvelle espèce
        Species species = new Species(null, "Charmander", "Fire", 80, null, null);
        Species savedSpecies = speciesService.save(species);

        // Rechercher l'espèce par ID
        Optional<Species> foundSpecies = speciesService.findById(savedSpecies.getId());

        // Vérifier que l'espèce est trouvée
        assertTrue(foundSpecies.isPresent());
        assertEquals(savedSpecies.getId(), foundSpecies.get().getId());
        assertEquals("Charmander", foundSpecies.get().getName());
    }

    @Test
    public void testFindAllSpecies() {
        // Sauvegarder quelques espèces
        speciesService.save(new Species(null, "Bulbasaur", "Grass", 90, null, null));
        speciesService.save(new Species(null, "Squirtle", "Water", 95, null, null));

        // Récupérer toutes les espèces
        List<Species> speciesList = speciesService.findAll();

        // Vérifier que la liste n'est pas vide et contient au moins 2 espèces
        assertNotNull(speciesList);
        assertTrue(speciesList.size() >= 2);
    }

    @Test
    public void testFindSpeciesByPokemonId() {
        // Étape 1: Créer et sauvegarder une espèce
        Species species = new Species();
        species.setName("Eevee");
        species.setType("Normal");
        species.setInitialHealthPoints(100);

        Species savedSpecies = speciesService.save(species);

        // Étape 2: Créer et sauvegarder un Pokémon lié à cette espèce
        Pokemon pokemon = new Pokemon();
        pokemon.setName("Pikachu");
        pokemon.setLevel(5);
        pokemon.setExperience(120);
        pokemon.setHealthPoints(35);
        pokemon.setMaxHealthPoints(35);
        pokemon.setSpecies(savedSpecies); // Lier ce Pokémon à l'espèce
        // Assurez-vous que le constructeur ou les setters pour Pokémon sont correctement configurés pour cet usage.

        Pokemon savedPokemon = pokemonService.save(pokemon);

        // Étape 3: Rechercher l'espèce par ID de Pokémon
        List<Species> foundSpecies = speciesService.findSpeciesByPokemonId(savedPokemon.getId());

        // Vérifier que l'espèce est trouvée
        assertNotNull(foundSpecies);
        assertEquals(1, foundSpecies.size());
    }

//    @Test
//    public void testFindSpeciesByAttackId() {
//        // Étape 1: Créer et sauvegarder une espèce
//        Species species = new Species();
//        species.setName("Eevee");
//        species.setType("Normal");
//        species.setInitialHealthPoints(100);
//
//        Species savedSpecies = speciesService.save(species);
//
//        // Étape 2: Créer et sauvegarder un Pokémon lié à cette espèce
//        Pokemon pokemon = new Pokemon();
//        pokemon.setName("Pikachu");
//        pokemon.setLevel(5);
//        pokemon.setExperience(120);
//        pokemon.setHealthPoints(35);
//        pokemon.setMaxHealthPoints(35);
//        pokemon.setSpecies(savedSpecies); // Lier ce Pokémon à l'espèce
//        // Assurez-vous que le constructeur ou les setters pour Pokémon sont correctement configurés pour cet usage.
//
//        Pokemon savedPokemon = pokemonService.save(pokemon);
//
//        // Étape 3: Créer et sauvegarder une attaque liée à cette espèce
//        Attack attack = new Attack();
//        attack.setAttackName("plantball");
//        attack.setType("grass");
//        attack.setDamage(34);
//        attack.setPokemons(savedPokemon); // Lier cette attaque au Pokémon
//        attack.setSpecies(savedSpecies); // Lier cette attaque à l'espèce
//        // Assurez-vous que le constructeur ou les setters pour Attaque sont correctement configurés pour cet usage.
//
//        Attack savedAttack = attackService.save(attack);
//
//        // Étape 3: Rechercher l'espèce par ID d'attaque
//        List<Species> foundSpecies = speciesService.findSpeciesByAttackId(savedAttack.getId());
//
//        // Vérifier que l'espèce est trouvée
//        assertNotNull(foundSpecies);
//        assertEquals(1, foundSpecies.size());
//    }

    @Test
    public void testDeleteSpeciesById() {
        // Sauvegarder une nouvelle espèce
        Species species = new Species(null, "Meowth", "Normal", 70, null, null);
        Species savedSpecies = speciesService.save(species);

        // Essayer de supprimer l'espèce
        try {
            speciesService.deleteById(savedSpecies.getId());

            // Vérifier que l'espèce a été supprimée
            Optional<Species> deletedSpecies = speciesService.findById(savedSpecies.getId());
            assertFalse(deletedSpecies.isPresent());
        } catch (Exception e) {
            // Si une exception se produit (par exemple, espèce liée à un Pokémon), échouer le test
            fail("Une exception est survenue lors de la suppression de l'espèce : " + e.getMessage());
        }
    }
}
