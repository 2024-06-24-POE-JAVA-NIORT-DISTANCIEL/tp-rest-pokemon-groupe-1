package tp_group1.spring_boot_pokemon;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tp_group1.spring_boot_pokemon.model.Species;
import tp_group1.spring_boot_pokemon.service.SpeciesService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SpeciesServiceTest {

    @Autowired
    private SpeciesService speciesService;

    @Test
    public void testSaveSpecies() {
        // Créer une nouvelle espèce
        Species species = new Species(null, "Pikachu", "Electric", 100, null);
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
        Species species = new Species(null, "Charmander", "Fire", 80, null);
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
        speciesService.save(new Species(null, "Bulbasaur", "Grass", 90, null));
        speciesService.save(new Species(null, "Squirtle", "Water", 95, null));

        // Récupérer toutes les espèces
        List<Species> speciesList = speciesService.findAll();

        // Vérifier que la liste n'est pas vide et contient au moins 2 espèces
        assertNotNull(speciesList);
        assertTrue(speciesList.size() >= 2);
    }

    @Test
    public void testDeleteSpeciesById() {
        // Sauvegarder une nouvelle espèce
        Species species = new Species(null, "Meowth", "Normal", 70, null);
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

    @Test
    public void testDeleteSpeciesLinkedToPokemon() {
        // Ce test suppose qu'il existe une logique pour lier une espèce à un Pokémon,
        // ce qui empêcherait la suppression. Vous devrez créer un Pokémon lié à une espèce pour ce test.
        Species species = new Species(1L, "Eevee", "Normal", 100, null);
        Species savedSpecies = speciesService.save(species);

        // Supposons que nous ayons une méthode pour lier un Pokémon à cette espèce.
        // Cette partie est du pseudocode car nous n'avons pas les détails de l'implémentation :
        // pokemonService.createPokemonLinkedToSpecies(savedSpecies);

        // Essayer de supprimer l'espèce liée à un Pokémon
        try {
            speciesService.deleteById(savedSpecies.getId());
            fail("Une exception était attendue lors de la suppression d'une espèce liée à un Pokémon");
        } catch (Exception e) {
            // Comportement attendu, car l'espèce est liée à un Pokémon
            assertTrue(e.getMessage().contains("Cannot delete species linked to Pokémon"));
        }
    }
}
