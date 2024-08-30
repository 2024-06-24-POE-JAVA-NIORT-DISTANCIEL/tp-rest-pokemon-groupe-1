package tp_group1.spring_boot_pokemon.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tp_group1.spring_boot_pokemon.dao.PokemonDao;
import tp_group1.spring_boot_pokemon.dao.SpeciesDao;

@SpringBootTest
public class SpeciesServiceTest {
	@Autowired
	private SpeciesService speciesService;

	@MockBean
	private SpeciesDao speciesDao;

	@MockBean
	private PokemonDao pokemonDao;

	@Test
	public void deleteById() throws Exception {
		Long id = 123L;
		speciesService.deleteById(id);
	}

	@Test
	public void deleteByIdTODO() throws Exception {
		Long id = 123L;
		speciesService.deleteById(id);
	}
}
