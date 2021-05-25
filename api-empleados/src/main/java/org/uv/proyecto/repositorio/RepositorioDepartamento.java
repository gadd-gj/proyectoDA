/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv.proyecto.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.proyecto.modelo.Departamento;

/**
 *
 * @author gaddiel
 */
public interface RepositorioDepartamento extends JpaRepository<Departamento, Long> {
    
}
