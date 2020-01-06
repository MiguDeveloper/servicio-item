package pe.tuna.servicioitem.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pe.tuna.servicioitem.clientes.IProductosClienteRest;
import pe.tuna.servicioitem.models.Item;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
@Primary
public class ItemServiceFeign implements IItemService {

    @Autowired
    private IProductosClienteRest clienteFeign;

    @Override
    public List<Item> findAll() {
        return clienteFeign.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, int cantidad) {
        return new Item(clienteFeign.detalle(id), cantidad);
    }
}
