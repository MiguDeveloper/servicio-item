package pe.tuna.servicioitem.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.tuna.servicioitem.models.Producto;

import java.util.List;

// Cuando no estamos usando ribbon para el balanceo de carga usamo la url con el puerto fijos
// de esta manera @FeignClient(name = "servicio-productos", url = "localhost:8001")
// y si usamos ribbon tendremos una configuracion en el application.properties
@FeignClient(name = "servicio-productos")
public interface IProductosClienteRest {

    @GetMapping("/listar")
    public List<Producto> listar();

    @GetMapping("/listar/{id}")
    public Producto detalle(@PathVariable Long id);
}
