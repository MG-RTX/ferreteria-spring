package com.distribuida.controller;

import com.distribuida.model.Producto;
import com.distribuida.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> findAll(){
        List<Producto> productos = productoService.findAll();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> findOne(@PathVariable int id){
        Producto producto = productoService.findOne(id);
        if (producto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<Producto> save(@RequestBody Producto producto){
        Producto productoNuevo = productoService.save(producto);
        return ResponseEntity.ok(productoNuevo);
    }

    @PutMapping("/producto/{id}")
    public ResponseEntity<Producto> update(
            @PathVariable int id,
            @PathVariable int idProveedor,
            @PathVariable int idCategoria,
            @RequestBody Producto producto
    ){
        Producto productoActualizado = productoService.update(id,idProveedor, idCategoria, producto);
        if (productoActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
