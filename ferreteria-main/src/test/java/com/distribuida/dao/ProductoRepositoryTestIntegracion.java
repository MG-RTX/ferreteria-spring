package com.distribuida.dao;

import com.distribuida.model.Proveedor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Producto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)

public class ProductoRepositoryTestIntegracion {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Test
    public void findAll(){
        List<Producto> productos = productoRepository.findAll();
        for (Producto item: productos){
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Producto>libro = productoRepository.findById(1);
        System.out.println(libro.orElse(null).toString());
    }

    @Test
    public void save(){
        Producto producto = new Producto();
        Optional<Categoria>categoria = categoriaRepository.findById(1);
        Optional<Proveedor>autor = proveedorRepository.findById(1);
        producto.setTitulo("Aventuras de Steff");
        producto.setEditorial("Nose");
        producto.setNumPaginas(1);
        producto.setEdicion("Primera");
        producto.setIdioma("Quechua");
        producto.setFechaPublicacion(new Date());
        producto.setDescripcion("Ara");
        producto.setTipoPasta("Amarrila");
        producto.setIsbn("ISBN");
        producto.setNumEjemplares(12);
        producto.setPortada("Roha");
        producto.setPresentacion("Buena");
        producto.setPrecio(12.0);
        producto.setCategoria(categoria.orElse(null));
        producto.setAutor(autor.orElse(null));

        productoRepository.save(producto);
    }

    @Test
    public void update(){
        Optional<Producto>libroexistente = productoRepository.findById(1);
        Optional<Categoria>categoria = categoriaRepository.findById(1);
        Optional<Proveedor>autor = proveedorRepository.findById(1);

        libroexistente.orElse(null).setTitulo("Steff");
        libroexistente.orElse(null).setEditorial("Noses");
        libroexistente.orElse(null).setNumPaginas(2);
        libroexistente.orElse(null).setEdicion("Steff");
        libroexistente.orElse(null).setIdioma("Ingles");
        libroexistente.orElse(null).setFechaPublicacion(new Date());
        libroexistente.orElse(null).setDescripcion("Steff");
        libroexistente.orElse(null).setTipoPasta("Roja");
        libroexistente.orElse(null).setIsbn("ISBN");
        libroexistente.orElse(null).setNumEjemplares(2);
        libroexistente.orElse(null).setPortada("Mala");
        libroexistente.orElse(null).setPresentacion("Regular");
        libroexistente.orElse(null).setPrecio(30.0);
        libroexistente.orElse(null).setCategoria(categoria.orElse(null));
        libroexistente.orElse(null).setAutor(autor.orElse(null));

        productoRepository.save(libroexistente.orElse(null));
    }

    @Test
    public void delete(){
        if(productoRepository.existsById(1)){
            productoRepository.deleteById(1);
        }
    }

}
