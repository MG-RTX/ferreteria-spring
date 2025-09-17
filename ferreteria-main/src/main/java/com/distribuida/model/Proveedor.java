package com.distribuida.model;


import jakarta.persistence.*;

@Entity
@Table (name="proveedor")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_proveedor")
    private int idProveedor;
    @Column(name="nombre")
    private String nombre;
    @Column(name="pais")
    private String pais;
    @Column(name="telefono")
    private String telefono;

    public Proveedor() {
    }

    public Proveedor(int idProveedor, String nombre, String pais, String telefono) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.pais = pais;
        this.telefono = telefono;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "idProveedor=" + idProveedor +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
