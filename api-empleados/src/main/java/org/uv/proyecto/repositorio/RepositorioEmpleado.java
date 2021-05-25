/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv.proyecto.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.uv.proyecto.modelo.Empleado;

/**
 *
 * @author gaddiel
 */
@Repository
public interface RepositorioEmpleado extends JpaRepository<Empleado, Long> {

}
