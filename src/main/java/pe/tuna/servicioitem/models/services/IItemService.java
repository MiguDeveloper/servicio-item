package pe.tuna.servicioitem.models.services;

import pe.tuna.serviciocommons.models.entity.Producto;
import pe.tuna.servicioitem.models.Item;

import java.util.List;

public interface IItemService {
    public List<Item> findAll();

    public Item findById(Long id, int cantidad);

    public Producto save(Producto producto);

    public Producto update(Producto producto, Long id);

    public void delete(Long id);
}
