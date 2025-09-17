package com.distribuida.service;

import com.distribuida.dao.ProveedorRepository;
import com.distribuida.dao.CategoriaRepository;
import com.distribuida.dao.ProductoRepository;
import com.distribuida.model.Proveedor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServicioTestUnitaria {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProductoServiceImpl libroServices;
    private CategoriaServiceImpl categoriaServices;
    private ProveedorServiceImpl autorServices;

    private Categoria categoria;
    private Producto producto;
    private Proveedor proveedor;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        producto = new Producto(1,"Romeo","Santillan",12,"Segunda","Ingles",new Date(),"Largo","Amarrilla","ISBN",12,"Mala","Buena",12.22,categoria, proveedor);
        proveedor = new Proveedor(1,"Marco","Chupin","Colombia","Tumbaco","1800","test@gmail.com");
        categoria = new Categoria(1,"Drama","Amores");
    }

    @Test
    public void testFindAll(){
       when(productoRepository.findAll()).thenReturn(Arrays.asList(producto));
       List<Producto> productos = libroServices.findAll();
       assertNotNull(productos);
       assertEquals(1, productos.size());
       verify(productoRepository,times(1)).findAll();
    }

    @Test
    public void testFindOne(){
        when(productoRepository.findById(1)).thenReturn(java.util.Optional.of(this.producto));
        Producto producto = libroServices.findOne(1);
        assertNotNull(producto);
        assertEquals("Romeo", producto.getTitulo());
        verify(productoRepository,times(1)).findById(1);
    }

    @Test
    public void Save(){
        when(productoRepository.save(producto)).thenReturn(producto);
        Producto producto1 = libroServices.save(producto);
        assertNotNull(producto1);
        assertEquals("Romeo", producto1.getTitulo());
        verify(productoRepository,times(1)).save(producto);
    }

    @Test
    public void update(){
        Producto libroactualizado = new Producto(1,"Julieta","Castle",12,"Segunda","Ingles",new Date(),"Largo","Amarrilla","ISBN",12,"Mala","Buena",12.22,categoria, proveedor);
        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));
        when(productoRepository.save(any(Producto.class))).thenReturn(libroactualizado);
        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedor));
        Producto producto1 = libroServices.update(1,1,1,libroactualizado);
        assertNotNull(producto1);
        assertEquals("Julieta", producto1.getTitulo());
        assertEquals("Castle", producto1.getEditorial());
        assertEquals(12, producto1.getNumPaginas());
        assertEquals("Segunda", producto1.getEdicion());
        assertEquals("Ingles", producto1.getIdioma());
        assertEquals("Largo", producto1.getDescripcion());
        assertEquals("Amarrilla", producto1.getTipoPasta());
        assertEquals("ISBN", producto1.getIsbn());
        assertEquals(12.22, producto1.getPrecio());
        assertEquals(1, producto1.getCategoria().getIdCategoria());
        assertEquals(1, producto1.getAutor().getIdAutor());
        verify(productoRepository).save(any(Producto.class));
        verify(categoriaRepository).findById(1);
        verify(proveedorRepository).findById(1);
    }

    @Test
    public void testDelete(){
        when(productoRepository.existsById(1)).thenReturn(false);
        libroServices.delete(1);
        verify(productoRepository,times(0)).deleteById(1);
    }

    @Test
    public void testFindOneNoExiste(){
        when(productoRepository.findById(2)).thenReturn(java.util.Optional.empty());
        Producto producto = libroServices.findOne(2);
    }


}
