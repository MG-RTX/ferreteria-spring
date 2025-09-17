package com.distribuida.controller;

import com.distribuida.model.Proveedor;
import com.distribuida.service.ProveedorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProveedorControllerTestUntiarias {
    @InjectMocks
    private ProveedorController proveedorController;

    @Mock
    private ProveedorService proveedorService;

    private Proveedor proveedor;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        proveedor = new Proveedor();
        proveedor.setIdAutor(1);
        proveedor.setNombre("Roberto");
        proveedor.setApellido("Firmino");
        proveedor.setDireccion("Brasil");
        proveedor.setTelefono("1800");
        proveedor.setCorreo("proveedor@gmail.com");
    }

    @Test
    public void testFindAll(){
        when(proveedorService.findAll()).thenReturn(List.of(proveedor));
        ResponseEntity<List<Proveedor>> respuesta = proveedorController.findAll();
        assertEquals(200,respuesta.getStatusCodeValue());
        assertEquals(1,respuesta.getBody().size());
        verify(proveedorService, times(1)).findAll();
    }

    @Test
    public void testFindOne(){
        when(proveedorService.findOne(1)).thenReturn(proveedor);
        ResponseEntity<Proveedor> respuesta = proveedorController.findOne(1);
        assertEquals(200,respuesta.getStatusCodeValue());
        assertEquals(proveedor.getNombre(),respuesta.getBody().getNombre());
    }

    @Test
    public void testOneNoExistente(){
        when(proveedorService.findOne(2)).thenReturn(null);
        ResponseEntity<Proveedor> respuesta = proveedorController.findOne(2);
        assertEquals(404,respuesta.getStatusCodeValue());
    }

    @Test
    public void testSave(){
        when(proveedorService.save(any(Proveedor.class))).thenReturn(proveedor);
        ResponseEntity<Proveedor> respuesta = proveedorController.save(proveedor);
        assertEquals(200,respuesta.getStatusCodeValue());
        assertEquals("Roberto", respuesta.getBody().getNombre());
    }

    @Test
    public void testUpdateExistente(){
        when(proveedorService.update(eq(1),any(Proveedor.class))).thenReturn(proveedor);
        ResponseEntity<Proveedor> respuesta = proveedorController.update(1, proveedor);
        assertEquals(200,respuesta.getStatusCodeValue());
    }

    @Test
    public void testUpdateNoExistente(){
        when(proveedorService.update(eq(2),any(Proveedor.class))).thenReturn(null);
        ResponseEntity<Proveedor> respuesta = proveedorController.update(2, proveedor);
        assertEquals(404,respuesta.getStatusCodeValue());
    }

    @Test
    public void testDelete(){
        doNothing().when(proveedorService).delete(1);
        ResponseEntity<Void> respuesta = proveedorController.delete(1);
        assertEquals(204,respuesta.getStatusCodeValue());
        verify(proveedorService, times(1)).delete(1);
    }

}
