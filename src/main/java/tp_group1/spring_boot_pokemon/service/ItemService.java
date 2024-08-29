package tp_group1.spring_boot_pokemon.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tp_group1.spring_boot_pokemon.dao.ItemDao;

@Service
public class ItemService {
    @Autowired
    private ItemDao itemDao;
}
