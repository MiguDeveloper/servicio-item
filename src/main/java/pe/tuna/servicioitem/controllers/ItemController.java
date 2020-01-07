package pe.tuna.servicioitem.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pe.tuna.servicioitem.models.Item;
import pe.tuna.servicioitem.models.Producto;
import pe.tuna.servicioitem.models.services.IItemService;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    @Qualifier("serviceFeign")
    private IItemService itemService;

    @GetMapping("/listar")
    public List<Item> listar() {
        return itemService.findAll();
    }

    // Aqui probamos el error que generamos en el microservicio productos, con timeout tambien
    @HystrixCommand(fallbackMethod = "metodoAlternativo")
    @GetMapping("/ver/{id}/cantidad/{cantidad}")
    public Item detalle(@PathVariable Long id, @PathVariable int cantidad) {
        return itemService.findById(id, cantidad);
    }

    public Item metodoAlternativo(Long id, int cantidad){
        Item item = new Item();
        item.setCantidad(cantidad);

        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre("PRODUCTO DEFAULT");
        producto.setPrecio(500.00);

        item.setProducto(producto);

        return item;
    }
}
