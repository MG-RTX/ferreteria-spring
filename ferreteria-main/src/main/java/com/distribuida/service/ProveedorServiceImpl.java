package com.distribuida.service;

import com.distribuida.dao.ProveedorRepository;
import com.distribuida.model.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    @Override
    public Proveedor findOne(int id) {
        Optional<Proveedor>proveedor= proveedorRepository.findById(id);
        return proveedor.orElse(null);
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);

    }

    @Override
    public Proveedor update(int id, Proveedor proveedorNuevo) {
        Proveedor proveedorExistente = findOne(id);

        if(proveedorExistente == null){
            return null;
        }

        proveedorExistente.setIdProveedor(proveedorNuevo.getIdProveedor());
        proveedorExistente.setNombre(proveedorNuevo.getNombre());
        proveedorExistente.setPais(proveedorNuevo.getPais());
        proveedorExistente.setTelefono(proveedorNuevo.getTelefono());
        return proveedorRepository.save(proveedorExistente);

    }

    @Override
    public void delete(int id) {
        if(proveedorRepository.existsById(id)){
            proveedorRepository.deleteById(id);
        }
    }
}
