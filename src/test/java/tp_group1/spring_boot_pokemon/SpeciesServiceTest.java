package tp_group1.spring_boot_pokemon;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tp_group1.spring_boot_pokemon.model.Attack;
import tp_group1.spring_boot_pokemon.model.Pokemon;
import tp_group1.spring_boot_pokemon.model.Species;
import tp_group1.spring_boot_pokemon.service.AttackService;
import tp_group1.spring_boot_pokemon.service.PokemonService;
import tp_group1.spring_boot_pokemon.service.SpeciesService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SpeciesServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(SpeciesServiceTest.class);

    @Autowired
    private SpeciesService speciesService;

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private AttackService attackService;

    private Species testSpecies1;
    private Species testSpecies2;
    private Species testSpecies3;
    private Pokemon testPokemon;
    private Attack testAttack;

    @BeforeEach
    public void setUp() {
        logger.info("Initialisation des données pour le test...");
        testSpecies1 = new Species(null, "TestSpecies1", "TestType1", 50, null, null);
        speciesService.save(testSpecies1);
        testSpecies2 = new Species(null, "TestSpecies2", "TestType2", 50, null, null);
        speciesService.save(testSpecies2);
        testSpecies3 = new Species(null, "TestSpecies3", "TestType3", 50, null, null);
        speciesService.save(testSpecies3);

        testPokemon = new Pokemon();
        testPokemon.setName("TestPokemon");
        testPokemon.setLevel(5);
        testPokemon.setExperience(120);
        testPokemon.setHealthPoints(35);
        testPokemon.setMaxHealthPoints(35);
        testPokemon.setSpecies(testSpecies1);
        pokemonService.save(testPokemon);

        testAttack = new Attack();
        testAttack.setAttackName("TestAttack");
        testAttack.setType("TestType");
        testAttack.setDamage(30);
        testAttack.setSpecies(testSpecies1);
        attackService.save(testAttack);
    }

    @AfterEach
    public void tearDown() {
        logger.info("Nettoyage des données après le test...");
        attackService.deleteById(1L);  // Suppression des attaques
        pokemonService.deleteById(1L);  // Suppression des Pokémon
        speciesService.deleteById(1L);  // Suppression des espèces
    }

    @Test
    public void testSaveSpecies() {
        logger.info("Exécution de testSaveSpecies...");
        Species species = new Species(null, "Pikachu", "Electric", 100, null, null);
        Species savedSpecies = speciesService.save(species);

        assertNotNull(savedSpecies.getId(), "L'ID de l'espèce ne doit pas être nul après la sauvegarde.");
        assertEquals("Pikachu", savedSpecies.getName(), "Le nom de l'espèce doit correspondre.");
        assertEquals("Electric", savedSpecies.getType(), "Le type de l'espèce doit correspondre.");
        assertEquals(Integer.valueOf(100), savedSpecies.getInitialHealthPoints(), "Les points de vie initiaux doivent correspondre.");
    }

    @Test
    public void testFindSpeciesById() {
        logger.info("Exécution de testFindSpeciesById...");
        Optional<Species> foundSpecies = speciesService.findById(testSpecies1.getId());

        assertTrue(foundSpecies.isPresent(), "L'espèce devrait être trouvée avec un ID valide.");
        assertEquals(testSpecies1.getId(), foundSpecies.get().getId(), "L'ID de l'espèce trouvée doit correspondre à l'ID sauvegardé.");
        assertEquals(testSpecies1.getName(), foundSpecies.get().getName(), "Le nom de l'espèce trouvée doit correspondre.");
    }

    @Test
    public void testFindAllSpecies() {
        logger.info("Exécution de testFindAllSpecies...");
        List<Species> speciesList = speciesService.findAllSpecies();
        logger.info("Liste des espèces : " + speciesList);

        assertNotNull(speciesList, "La liste des espèces ne doit pas être nulle.");
        assertTrue(speciesList.size() > 0, "La liste des espèces doit contenir au moins une espèce.");

        // Affiche l'ID de testSpecies3 pour vérifier s'il est dans la liste
        logger.info("ID de testSpecies3 : " + testSpecies3.getId());

        // Vérifie si l'ID de testSpecies3 est présent dans les IDs récupérés
        boolean containsSpecies3 = speciesList.stream()
                .anyMatch(species -> species.getId().equals(testSpecies3.getId()));
        assertTrue(containsSpecies3, "La liste des espèces doit contenir l'espèce de test.");
    }


    @Test
    public void testFindSpeciesByPokemonId() {
        logger.info("Exécution de testFindSpeciesByPokemonId...");

        List<Species> foundSpecies = speciesService.findSpeciesByPokemonId(testPokemon.getId());

        assertNotNull(foundSpecies, "La recherche d'espèces par ID de Pokémon ne doit pas retourner null.");
        assertEquals(1, foundSpecies.size(), "Une seule espèce doit être trouvée pour un ID de Pokémon donné.");
        assertEquals(testSpecies1.getId(), foundSpecies.get(0).getId(), "L'ID de l'espèce trouvée doit correspondre à l'espèce de test.");
    }

    @Test
    public void testDeleteSpeciesByIdSuccess() {
        logger.info("Exécution de testDeleteSpeciesByIdSuccess...");
        Species species = new Species(null, "Meowth", "Normal", 70, null, null);
        Species savedSpecies = speciesService.save(species);

        speciesService.deleteById(savedSpecies.getId());

        Optional<Species> deletedSpecies = speciesService.findById(savedSpecies.getId());
        assertFalse(deletedSpecies.isPresent(), "L'espèce devrait être supprimée.");
    }

    @Test
    public void testDeleteSpeciesByIdFailure() {
        logger.info("Exécution de testDeleteSpeciesByIdFailure...");

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            speciesService.deleteById(testSpecies1.getId());
        });

        String expectedMessage = "Species is linked to a Pokémon and cannot be deleted.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "L'exception devrait indiquer que l'espèce est liée à un Pokémon.");
    }

    @Test
    public void testFindSpeciesByAttackId() {
        logger.info("Exécution de testFindSpeciesByAttackId...");

        List<Species> foundSpecies = speciesService.findSpeciesByAttackId(testAttack.getId());

        assertNotNull(foundSpecies, "La recherche d'espèces par ID d'attaque ne doit pas retourner null.");
        assertEquals(1, foundSpecies.size(), "Une seule espèce doit être trouvée pour un ID d'attaque donné.");
        assertEquals(testSpecies1.getId(), foundSpecies.get(0).getId(), "L'ID de l'espèce trouvée doit correspondre à l'espèce associée à l'attaque.");
        assertEquals(testSpecies1.getName(), foundSpecies.get(0).getName(), "Le nom de l'espèce trouvée doit correspondre.");
    }
}
