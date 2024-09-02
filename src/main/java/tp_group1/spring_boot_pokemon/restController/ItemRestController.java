package tp_group1.spring_boot_pokemon.restController;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import tp_group1.spring_boot_pokemon.model.Item;
import tp_group1.spring_boot_pokemon.service.ItemService;
import java.util.List;
@RestController
@RequestMapping("/items")
public class ItemRestController {

    @Autowired
    private ItemService itemService;


    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemService.findById(id).orElse(null);
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemService.save(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteById(id);
    }


    @GetMapping
    public List<Item> getAllItems(@RequestParam(required = false) Integer minCost,
                                  @RequestParam(required = false) Integer maxCost) {
       if (minCost != null && maxCost != null) {
           return itemService.findByCostRange(minCost, maxCost);
       }
       return itemService.findAll();
    }
}
