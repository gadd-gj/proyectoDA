/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv.proyecto.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uv.proyecto.excepcion.RecursoNoEncontrado;
import org.uv.proyecto.modelo.Empleado;
import org.uv.proyecto.repositorio.RepositorioEmpleado;

/**
 *
 * @author gaddiel
 */
@RestController
@RequestMapping("/uv")
public class ControladorEmpleado {

    @Autowired
    private RepositorioEmpleado repositorioEmpleado;

    @GetMapping("/empleados")
    public List<Empleado> showAll() {
        return repositorioEmpleado.findAll();
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> searchById(@PathVariable(value = "id") Long id) throws RecursoNoEncontrado {

        Empleado empleado = repositorioEmpleado.findById(id).orElseThrow(
                () -> new RecursoNoEncontrado("No se encontró el id : " + id)
        );

        return ResponseEntity.ok().body(empleado);
    }

    @PostMapping("/empleados")
    public Empleado create(@Valid @RequestBody Empleado empleado) {
        return repositorioEmpleado.save(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Empleado updateEmpleado)
            throws RecursoNoEncontrado {

        Empleado empleado = repositorioEmpleado.findById(id).orElseThrow(
                () -> new RecursoNoEncontrado("No se encontró el id : " + id)
        );

        empleado.setNombre(updateEmpleado.getNombre());
        empleado.setDireccion(updateEmpleado.getDireccion());
        empleado.setTelefono(updateEmpleado.getTelefono());
        empleado.setDepartamento(updateEmpleado.getDepartamento());

        final Empleado e = repositorioEmpleado.save(empleado);
        return ResponseEntity.ok(e);

    }

    @DeleteMapping("/empleados/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") Long id) throws RecursoNoEncontrado {

        Empleado empleado = repositorioEmpleado.findById(id).orElseThrow(
                () -> new RecursoNoEncontrado("No se encontró el id : " + id)
        );

        repositorioEmpleado.delete(empleado);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("delete", Boolean.TRUE);
        return respuesta;
    }

}
