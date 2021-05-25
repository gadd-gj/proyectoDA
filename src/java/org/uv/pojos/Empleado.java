/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv.pojos;

/**
 *
 * @author gusky
 */
public class Empleado {
    private Long id;
    private Long idEmpleado;
    private String nombre;
    private String direccion;
    private String telefono;
    private Long idDepartamento;
    private Long departamento;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    
 

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
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

    public void setIdDepartamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Long getIdDepartamento() {
        return idDepartamento;
    }

    public void setDepartamento(Long departamento) {
        this.departamento = departamento;
    }

    public Long getDepartamento() {
        return departamento;
    }

 
    
    
    
    
}
