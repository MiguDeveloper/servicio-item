package pe.tuna.servicioitem.models.services;

import pe.tuna.servicioitem.models.Item;

import java.util.List;

public interface IItemService {
    public List<Item> findAll();
    public Item findById(Long id, int cantidad);
}
