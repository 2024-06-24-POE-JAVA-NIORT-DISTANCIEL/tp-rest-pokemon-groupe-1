package tp_group1.spring_boot_pokemon.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tp_group1.spring_boot_pokemon.dao.ItemDao;
import tp_group1.spring_boot_pokemon.model.Item;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemDao itemDao;

    //methode pour créer et sauvegarder un objet
    @Transactional
    public Item save(Item item) {
        return itemDao.save(item);
    }

    //methode trouver un objet par son id
    public Optional<Item> findById(Long id) {
        return itemDao.findById(id);
    }

    //methode pour trouver tous les objets
    public List<Item> findAll() {
        return itemDao.findAll();
    }

    //methode supprimer un objet par son id
    @Transactional
    public void deleteById(Long id) {
        itemDao.deleteById(id);
    }

}
