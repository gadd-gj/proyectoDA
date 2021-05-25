/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv.cliente.pojo;

/**
 *
 * @author gaddiel
 */
public class Empleado {

    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private Long idDepartamento;
    private Departamento departamento;

    public Empleado() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
        setDepartamento(this.idDepartamento);
    }

    private void setDepartamento(Long idDepartamento) {
        departamento = new Departamento();
        this.departamento.setIdDepartamento(idDepartamento);
        this.departamento.setNombre("");
    }
    
    public Departamento getDepartamento() {
        return departamento;
    }

    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + ", idDepartamento=" + idDepartamento + ", departamento=" + departamento + '}';
    }

    

}
