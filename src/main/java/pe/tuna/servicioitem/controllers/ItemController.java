package pe.tuna.servicioitem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pe.tuna.servicioitem.models.Item;
import pe.tuna.servicioitem.models.services.IItemService;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private IItemService itemService;

    @GetMapping("/listar")
    public List<Item> listar() {
        return itemService.findAll();
    }

    @GetMapping("/ver/{id}/cantidad/{cantidad}")
    public Item detalle(@PathVariable Long id, @PathVariable int cantidad) {
        return itemService.findById(id, cantidad);
    }
}
