/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv.cliente.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.uv.cliente.pojo.Empleado;

/**
 *
 * @author gaddiel
 */
public class EmpleadoDao {

    private static final String POST_EMPLEADO = "http://172.10.0.20:8080/uv/empleados";
    private static final String GET_EMPLEADOS = "http://172.10.0.20:8080/uv/empleados";
    private static final String PUT_EMPLEADO = "http://172.10.0.20:8080/uv/empleados/{id}";
    private static final String DELETE_EMPLEADO = "http://172.10.0.20:8080/uv/empleados/{id}";
    private static final String GET_EMPLEADO = "http://172.10.0.20:8080/uv/empleados/{id}";

    private RestTemplate restTemplate;

    public EmpleadoDao() {
        restTemplate = new RestTemplate();
    }

    public ResponseEntity getEmpleados() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity< String> entity = new HttpEntity< String>("parameters", headers);

        ResponseEntity< String> result = restTemplate.exchange(GET_EMPLEADOS, HttpMethod.GET, entity,
                String.class);

        return result;
    }

    public Empleado getEmpleado(String id) {

        Map< String, Long> params = new HashMap< String, Long>();
        params.put("id", Long.parseLong(id));

        RestTemplate restTemplate = new RestTemplate();
        Empleado result = restTemplate.getForObject(GET_EMPLEADO, Empleado.class, params);

        return result;
    }

    public Empleado create(Empleado empleado) {

        RestTemplate restTemplate = new RestTemplate();
        Empleado result = restTemplate.postForObject(POST_EMPLEADO, empleado, Empleado.class);

        return result;
    }

    public void update(Empleado empleado) {
        Map< String, Long> params = new HashMap< String, Long>();

        params.put("id", empleado.getId());

        Empleado updatedEmpleado = new Empleado();

        updatedEmpleado.setNombre(empleado.getNombre());
        updatedEmpleado.setDireccion(empleado.getDireccion());
        updatedEmpleado.setTelefono(empleado.getTelefono());
        updatedEmpleado.setIdDepartamento(empleado.getIdDepartamento());

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(PUT_EMPLEADO, updatedEmpleado, params);
    }

    public void delete(String id) {
        Map< String, Long> params = new HashMap< String, Long>();
        params.put("id", Long.parseLong(id));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(DELETE_EMPLEADO, params);
    }

    public List<Empleado> obtenerTodo() {
        HttpHeaders headers = new HttpHeaders();
        Empleado empleado = new Empleado();

        HttpEntity<Empleado> entity = new HttpEntity<Empleado>(empleado, headers);

        ResponseEntity<List<Empleado>> result = restTemplate.exchange(
                GET_EMPLEADOS, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Empleado>>() {
        });
        List<Empleado> empleados = result.getBody();
        
        return empleados;
    }
}
