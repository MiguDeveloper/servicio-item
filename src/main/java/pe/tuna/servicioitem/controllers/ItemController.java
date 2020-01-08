package pe.tuna.servicioitem.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pe.tuna.servicioitem.models.Item;
import pe.tuna.servicioitem.models.Producto;
import pe.tuna.servicioitem.models.services.IItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// RefreshScope nos permite actualizar los components, beans, enviroments, etc que vengan con 'values'
@RefreshScope
@RestController
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    // inyectamos el beans de spring de enviroment
    @Autowired
    private Environment environment;

    @Autowired
    @Qualifier("serviceFeign")
    private IItemService itemService;

    // inyectamos las properties que tenemos en el servidor de configuracion
    @Value("${configuracion.texto}")
    private String texto;

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

    public Item metodoAlternativo(Long id, int cantidad) {
        Item item = new Item();
        item.setCantidad(cantidad);

        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre("PRODUCTO DEFAULT");
        producto.setPrecio(500.00);

        item.setProducto(producto);

        return item;
    }

    @GetMapping("/obtener-config")
    public ResponseEntity<?> obtenerConfiguracion(@Value("${server.port}") String puerto) {

        Map<String, String> json = new HashMap<>();
        json.put("texto", texto);
        json.put("puerto", puerto);

        logger.info("TEXTO: " + json.get("texto"));
        logger.info("PUERTO: " + json.get("puerto"));

        if (environment.getActiveProfiles().length > 0 && environment.getActiveProfiles()[0].equals("dev")){
            json.put("autor.nombre", environment.getProperty("configuracion.autor.nombre"));
            json.put("autor.email", environment.getProperty("configuracion.autor.email"));
        }

        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }
}
