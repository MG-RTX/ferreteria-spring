package com.distribuida.dao;

import com.distribuida.model.Proveedor;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class ProveedorRepositorioTestIntegracion {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Test
    public void findAll(){
        List<Proveedor> proveedor = proveedorRepository.findAll();
        assertNotNull(proveedor);
        assertTrue(proveedor.size()>0);

        for(Proveedor item: proveedor){
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Proveedor> autor = proveedorRepository.findById(1);
        assertTrue(autor.isPresent(),"El Proveedor con id= 1, deberia existir");
        System.out.println(autor.toString());
    }

    @Test
    public  void save(){
        Proveedor proveedor = new Proveedor(1,"Lucho","Diaz","Ecuador","Pifo","911","mg@gmail.com");
        proveedorRepository.save(proveedor);
        assertNotNull(proveedor.getIdAutor(),"El proveedor guardado debe tener ID");
        assertEquals("911", proveedor.getTelefono());
        assertEquals("Lucho", proveedor.getNombre());
    }

    @Test
    public void update(){
        Optional<Proveedor> autor = proveedorRepository.findById(1);

        assertTrue(autor.isPresent(),"El autor con ID=1 debe existir para actualizarse");
        autor.orElse(null).setNombre("Jona");
        autor.orElse(null).setApellido("Flores");
        autor.orElse(null).setDireccion("Tumbaco");
        autor.orElse(null).setTelefono("999");
        autor.orElse(null).setPais("Yaruqui");
        autor.orElse(null).setNombre("Jona");

        Proveedor autoractualizado = proveedorRepository.save(autor.orElse(null));

        assertEquals("Jona",autoractualizado.getNombre());
        assertEquals("Flores", autoractualizado.getApellido());

    }

    @Test
    public void delete(){
        if(proveedorRepository.existsById(50)){
            proveedorRepository.deleteById(50);
        }
        assertFalse(proveedorRepository.existsById(50));
    }

}
