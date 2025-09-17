package com.distribuida.controller;

import com.distribuida.model.Proveedor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Producto;
import com.distribuida.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;


//import static java.lang.reflect.Array.get;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("removal")
@WebMvcTest(ProductoController.class)
public class ProductoControllerTestIntegracion {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFinAll() throws Exception {
        Producto producto = new Producto(1,"Fenomeno","PEPE",12,"Mala","Español",new Date(),"Corta","Roja","ISBN",5,"Mala","Buena",12.23,new Categoria(), new Proveedor());

        Mockito.when(productoService.findAll()).thenReturn(List.of(producto));

        mockMvc.perform(get("/api/producto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Fenomeno"));
    }

    @Test
    public void testSave() throws Exception {
        Producto producto = new Producto(1,"Fenomeno","PEPE",12,"Mala","Español",new Date(),"Corta","Roja","ISBN",5,"Mala","Buena",12.23,new Categoria(), new Proveedor());

        Mockito.when(productoService.save(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/api/producto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto))
        )       .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Fenomeno"));

    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/api/libro/2")).andExpect(status().isNoContent());
    }
}
