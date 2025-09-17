package com.distribuida.dao;

import com.distribuida.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    Proveedor findByNombre(String nombre);
}
