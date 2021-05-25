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
import org.uv.cliente.pojo.Departamento;

/**
 *
 * @author gaddiel
 */
public class DepartamentoDao {

    private static final String POST_DEPARTAMENTO = "http://172.10.0.20:8080/uv/departamentos";
    private static final String GET_DEPARTAMENTOS = "http://172.10.0.20:8080/uv/departamentos";
    private static final String PUT_DEPARTAMENTO = "http://172.10.0.20:8080/uv/departamentos/{id}";
    private static final String DELETE_DEPARTAMENTO = "http://172.10.0.20:8080/uv/departamentos/{id}";
    private static final String GET_DEPARTAMENTO = "http://172.10.0.20:8080/uv/departamentos/{id}";

    private RestTemplate restTemplate;

    public DepartamentoDao() {
        restTemplate = new RestTemplate();
    }

    public ResponseEntity getDepartementos() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity< String> entity = new HttpEntity< String>("parameters", headers);

        ResponseEntity< String> result = restTemplate.exchange(GET_DEPARTAMENTOS, HttpMethod.GET, entity,
                String.class);

        return result;
    }

    public Departamento getDepartamento(String id) {

        Map< String, Long> params = new HashMap< String, Long>();
        params.put("id", Long.parseLong(id));

        RestTemplate restTemplate = new RestTemplate();
        Departamento result = restTemplate.getForObject(GET_DEPARTAMENTO, Departamento.class, params);

        return result;
    }

    public Departamento create(Departamento departamento) {

        RestTemplate restTemplate = new RestTemplate();
        Departamento result = restTemplate.postForObject(POST_DEPARTAMENTO, departamento, Departamento.class);

        return result;
    }

    public void update(Departamento departamento) {
        Map< String, Long> params = new HashMap< String, Long>();

        params.put("id", departamento.getIdDepartamento());

        Departamento updatedDepartamento = new Departamento();

        updatedDepartamento.setNombre(departamento.getNombre());

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(PUT_DEPARTAMENTO, updatedDepartamento, params);
    }

    public void delete(String id) {
        Map< String, String> params = new HashMap< String, String>();
        params.put("id", id);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(DELETE_DEPARTAMENTO, params);
    }

    public List<Departamento> obtenerTodo() {
        HttpHeaders headers = new HttpHeaders();
        Departamento departamento = new Departamento();

        HttpEntity<Departamento> entity = new HttpEntity<Departamento>(departamento, headers);

        ResponseEntity<List<Departamento>> result = restTemplate.exchange(
                GET_DEPARTAMENTOS, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Departamento>>() {
        }
        );
        List<Departamento> departamentos = result.getBody();

        return departamentos;
    }

    

}
