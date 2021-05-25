/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv.proyecto.modelo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author gaddiel
 */
@Entity
@Table(name = "departamentos")
public class Departamento {

    @Id
    @GeneratedValue
    private Long idDepartamento;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    public Departamento() {
    }

    public Long getId() {
        return idDepartamento;
    }

    public void setId(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
