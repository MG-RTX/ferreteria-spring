package com.distribuida.service;

import com.distribuida.dao.ProveedorRepository;
import com.distribuida.dao.CategoriaRepository;
import com.distribuida.dao.ProductoRepository;
import com.distribuida.model.Proveedor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<Producto> findAll(){
        return productoRepository.findAll();
    }

    @Override
    public Producto findOne(int id){
        Optional<Producto> libro = productoRepository.findById(id);
        return libro.orElse(null);
    }
    @Override
    public Producto save(Producto producto) {
        // Validate Producto
        Objects.requireNonNull(producto, "Producto no puede ser nulo");

        // Fetch and validate Proveedor
        Proveedor proveedor = Optional.ofNullable(producto.getProveedor())
                .map(a -> proveedorRepository.findById(a.getIdProveedor()))
                        .orElseThrow(() -> new RuntimeException("Proveedor no válido o sin ID"))
                        .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        producto.setProveedor(proveedor);

        // Fetch and validate Categoria
        Categoria categoria = Optional.ofNullable(producto.getCategoria())
                .map(c -> categoriaRepository.findById(c.getIdCategoria()))
                .orElseThrow(() -> new RuntimeException("Categoría no válida o sin ID"))
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        producto.setCategoria(categoria);

        return productoRepository.save(producto);
    }

    @Override
    public Producto update(int id, int idCategoria, int idProveedor, Producto producto){
        Producto productoExistente = findOne(id);

        Optional<Proveedor> proveedorExistente = proveedorRepository.findById(idProveedor);
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(idCategoria);

        if (productoExistente == null){
            return null;
        }
        if (proveedorExistente.isPresent()) {
            productoExistente.setProveedor(proveedorExistente.get());
        }
        if (categoriaExistente.isPresent()) {
            productoExistente.setCategoria(categoriaExistente.get());
        }


        productoExistente.setNombre(producto.getNombre());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setNumUnidades(producto.getNumUnidades());
        productoExistente.setPortada(producto.getPortada());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setCategoria(producto.getCategoria());
        productoExistente.setProveedor(producto.getProveedor());

        return productoRepository.save(productoExistente);

    }

    @Override
    public void delete(int id){
        if (productoRepository.existsById(id)){
            productoRepository.deleteById(id);
        }
    }

}
