/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv.clientejsp;


import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.uv.pojos.Empleado;

/**
 *
 * @author gusky
 */
public class RestClient {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://172.17.0.4:8080/uv/empleados";

    public RestClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI);
    }
    
    public String postJson(Object requestEntity){
       return webTarget.request(MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, MediaType.APPLICATION_JSON), String.class); 
    }
    public String deleteJson(Long clave) throws ClientErrorException {
           return webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{clave})).request().delete(String.class);
    }
    public String putJson(Object requestEntity, Long clave) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{clave})).request(MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, MediaType.APPLICATION_JSON), String.class);
    }
    GenericType<List<Empleado>> genericType = new GenericType<List<Empleado>>(){}; 
    public List<Empleado> getJson(List<Empleado> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(MediaType.APPLICATION_JSON).get(genericType);
    }
    public <T> T getOneJson(Class<T> responseType, String clave) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{clave}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }
    
    public void close() {
        client.close();
    }

   
}
