package pe.tuna.servicioitem.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pe.tuna.servicioitem.models.Item;
import pe.tuna.servicioitem.models.Producto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements IItemService {

    @Autowired
    private RestTemplate clienteRest;

    @Override
    public List<Item> findAll() {
        List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://servicio-productos/listar", Producto[].class));

        return productos.stream()
                .map(producto -> {
                    return new Item(producto, 1);
                }).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, int cantidad) {
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());

        Producto producto = clienteRest.getForObject("http://servicio-productos/listar/{id}", Producto.class, pathVariables);
        return new Item(producto, cantidad);
    }
}
