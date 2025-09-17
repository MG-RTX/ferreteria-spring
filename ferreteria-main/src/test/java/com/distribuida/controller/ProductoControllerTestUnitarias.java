package com.distribuida.controller;

import com.distribuida.model.Proveedor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Producto;
import com.distribuida.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class ProductoControllerTestUnitarias {

    @InjectMocks
    private ProductoController productoController;

    @Mock
    private ProductoService productoService;

    private Producto producto;
    private Proveedor proveedor;
    private Categoria categoria;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        producto = new Producto();
        producto.setIdLibro(1);
        producto.setTitulo("Si Señor");
        producto.setEditorial("YNWA");
        producto.setNumPaginas(300);
        producto.setEdicion("Segunda");
        producto.setIdioma("Español");
        producto.setFechaPublicacion(new Date());
        producto.setDescripcion("Larga");
        producto.setTipoPasta("Roja");
        producto.setIsbn("ISBN");
        producto.setNumEjemplares(300);
        producto.setPortada("Buena");
        producto.setPresentacion("Mala");
        producto.setPrecio(35.90);
        producto.setCategoria(new Categoria());
        producto.setAutor(new Proveedor());



    }

    @Test
    public void testFindAll(){
        when(productoService.findAll()).thenReturn(List.of(producto));
        ResponseEntity<List<Producto>> respuesta = productoController.findAll();
        assertEquals(200,respuesta.getStatusCodeValue());
        assertEquals(1,respuesta.getBody().size());
        verify(productoService, times(1)).findAll();
    }

    @Test
    public void testFindOne(){
        when(productoService.findOne(1)).thenReturn(producto);
        ResponseEntity<Producto> respuesta = productoController.findOne(1);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(producto.getAutor(),respuesta.getBody().getAutor());
    }

    @Test
    public void testOneNoExistente(){
        when(productoService.findOne(2)).thenReturn(null);
        ResponseEntity<Producto> respuesta = productoController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());
    }

    @Test
    public void testSave(){
        when(productoService.save(any(Producto.class))).thenReturn(producto);
        ResponseEntity<Producto> respuesta = productoController.save(producto);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("Si Señor", respuesta.getBody().getTitulo());
    }

    @Test
    public void testUpdateExistente() {
        when(productoService.update(
                eq(1),
                eq(1),
                eq(1),
                any(Producto.class)
        )).thenReturn(producto);

        ResponseEntity<Producto> respuesta = productoController.update(1, 1, 1, producto);

        assertEquals(200, respuesta.getStatusCodeValue());
    }

    @Test
    public void testUpdateNoExistente(){
        when(productoService.update(eq(1),eq(1),eq(1), any(Producto.class))).thenReturn(null);
        ResponseEntity<Producto> respuesta = productoController.update(2,2,2, producto);
        assertEquals(404, respuesta.getStatusCodeValue());
    }

    @Test
    public void testDelete(){
        doNothing().when(productoService).delete(1);
        ResponseEntity<Void> respuesta = productoController.delete(1);
        assertEquals(204, respuesta.getStatusCodeValue());
        verify(productoService,times(1)).delete(1);
    }



}
