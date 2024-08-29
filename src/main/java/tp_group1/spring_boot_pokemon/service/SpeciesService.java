package tp_group1.spring_boot_pokemon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp_group1.spring_boot_pokemon.dao.SpeciesDao;

@Service
public class SpeciesService {
    @Autowired
    private SpeciesDao speciesDao;
}
