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
import org.uv.proyecto.modelo.Departamento;
import org.uv.proyecto.repositorio.RepositorioDepartamento;

/**
 *
 * @author gaddiel
 */
@RestController
@RequestMapping("/uv")
public class ControladorDepartamento {

    @Autowired
    private RepositorioDepartamento repositorioDepartamento;

    @GetMapping("/departamentos")
    public List<Departamento> showAll() {
        return repositorioDepartamento.findAll();
    }

    @GetMapping("/departamentos/{id}")
    public ResponseEntity<Departamento> searchById(@PathVariable(value = "id") Long id) throws RecursoNoEncontrado {

        Departamento departamento = repositorioDepartamento.findById(id).orElseThrow(
                () -> new RecursoNoEncontrado("No se encontró el id : " + id)
        );

        return ResponseEntity.ok().body(departamento);
    }

    @PostMapping("/departamentos")
    public Departamento create(@Valid @RequestBody Departamento departamento) {
        return repositorioDepartamento.save(departamento);
    }

    @PutMapping("/departamentos/{id}")
    public ResponseEntity<Departamento> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Departamento updateDepartamento)
            throws RecursoNoEncontrado {

        Departamento departamento = repositorioDepartamento.findById(id).orElseThrow(
                () -> new RecursoNoEncontrado("No se encontró el id : " + id)
        );

        departamento.setNombre(updateDepartamento.getNombre());
        

        final Departamento dep = repositorioDepartamento.save(departamento);
        return ResponseEntity.ok(dep);

    }

    @DeleteMapping("/departamentos/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") Long id) throws RecursoNoEncontrado {

        Departamento departamento = repositorioDepartamento.findById(id).orElseThrow(
                () -> new RecursoNoEncontrado("No se encontró el id : " + id)
        );

        repositorioDepartamento.delete(departamento);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("delete", Boolean.TRUE);
        return respuesta;
    }

}
