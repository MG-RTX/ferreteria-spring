package com.distribuida.dao;

import com.distribuida.model.Carrito;
import com.distribuida.model.CarritoItem;
import com.distribuida.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarritoItemRepository extends JpaRepository<CarritoItem, Long> {
    Optional <CarritoItem> findByCarritoAndProducto(Carrito carrito, Producto producto);
    List<CarritoItem> findByCarrito( Carrito carrito);
}
