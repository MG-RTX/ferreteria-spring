package com.distribuida.service;

import com.distribuida.model.Producto;

import java.util.List;

public interface ProductoService {
    public List<Producto> findAll();
    public Producto findOne(int id);
    public Producto save(Producto producto);
    public Producto update(int id, int idCategoria, int idProveedor, Producto producto);
    public void delete(int id);
}
