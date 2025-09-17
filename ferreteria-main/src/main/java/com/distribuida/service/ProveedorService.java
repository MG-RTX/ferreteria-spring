package com.distribuida.service;

import com.distribuida.model.Proveedor;


import java.util.List;

public interface ProveedorService {

    public List<Proveedor> findAll();
    public Proveedor findOne(int id);
    public Proveedor save(Proveedor proveedor);
    public Proveedor update(int id, Proveedor proveedor);
    public void delete(int id);

}
