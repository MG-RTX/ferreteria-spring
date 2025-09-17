package com.distribuida.service;

import com.distribuida.dao.ProveedorRepository;
import com.distribuida.model.Proveedor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProveedorServicioTestUnitaria {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorServiceImpl autorService;

    private Proveedor proveedor;

    @BeforeEach
    public void setUp(){
        proveedor = new Proveedor();
        proveedor.setIdAutor(1);
        proveedor.setNombre("Xavi");
        proveedor.setApellido("Causa");
        proveedor.setPais("Peru");
        proveedor.setDireccion("Casa de Mama Charo");
        proveedor.setTelefono("Claro");
        proveedor.setCorreo("test@gmail.com");
    }

    @Test
    public void  testFindAll(){
        when(proveedorRepository.findAll()).thenReturn(List.of(proveedor));
        List<Proveedor> proveedors =autorService.findAll();
        assertNotNull(proveedors);
        assertEquals(1, proveedors.size());
        verify(proveedorRepository, times(1)).findAll();
    }

    @Test
    public void testFindOneExistente(){
        when(proveedorRepository.findById(1)).thenReturn(Optional.of(this.proveedor));
        Proveedor proveedor = autorService.findOne(1);
        assertNotNull(proveedor);
        assertEquals("Xavi", proveedor.getNombre());
    }

    @Test
    public void testFindOneNoExistente(){
        when(proveedorRepository.findById(2)).thenReturn(Optional.empty());
        Proveedor proveedor1 = autorService.findOne(2);
        assertNull(proveedor1);
    }

    @Test
    public void testSave(){
        when(proveedorRepository.save(proveedor)).thenReturn(proveedor);
        Proveedor proveedor1 = autorService.save(proveedor);
        assertNotNull(proveedor1);
        assertEquals("Xavi", proveedor1.getNombre());
    }

    @Test
    public void testUpdateExistente(){
        Proveedor proveedorActualizado = new Proveedor();

        proveedorActualizado.setNombre("Marco");
        proveedorActualizado.setApellido("Chupin");
        proveedorActualizado.setPais("Brasil");
        proveedorActualizado.setDireccion("Pifo");
        proveedorActualizado.setTelefono("9110");
        proveedorActualizado.setCorreo("test@gmai.com");

        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedor));
        when(proveedorRepository.save(any())).thenReturn(proveedorActualizado);
        Proveedor proveedorResultado = autorService.update(1, proveedorActualizado);
        assertNotNull(proveedorResultado);
        assertEquals("Marco", proveedorResultado.getNombre());
        verify(proveedorRepository, times(1)).save(proveedor);
    }

    @Test
    public void testUpdateNoExistente(){
       Proveedor proveedor1Nuevo = new Proveedor();
        when(proveedorRepository.findById(2)).thenReturn(Optional.empty());
        Proveedor proveedorResultado = autorService.update(2, proveedor1Nuevo);
        assertNull(proveedorResultado);
        verify(proveedorRepository, never()).save(any());
    }

    @Test
    public void testDeleteExistente(){
        when(proveedorRepository.existsById(1)).thenReturn(true);
        autorService.delete(1);
        verify(proveedorRepository).deleteById(1);
    }

    @Test
    public void testDeleteNoExistente(){
        when(proveedorRepository.existsById(2)).thenReturn(false);
        autorService.delete(2);
        verify(proveedorRepository, never()).deleteById(anyInt());
    }









}
