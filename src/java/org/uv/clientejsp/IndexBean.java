/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv.clientejsp;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.uv.pojos.Empleado;

/**
 *
 * @author gusky
 */
@ManagedBean
@SessionScoped
public class IndexBean {
    private Empleado empleado;
    RestClient rest = new RestClient();
    private List<Empleado> consulta;
    
    public IndexBean() {
        empleado = new Empleado();
    }

    public void ingresar(){
        rest.postJson(empleado);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Nuevo","Registro exitoso"));
        empleado = new Empleado(); 
    }
    public void mostrar(){
        consulta = rest.getJson(consulta);
       
    }
        public void eliminar(){
        rest.deleteJson(empleado.getIdEmpleado());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Eliminar", "Registro eliminado"));
        empleado=new Empleado();
    }
    
    public void actualizar(){
        rest.putJson(empleado, empleado.getIdEmpleado());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Actualizar", "Registro actualizado"));
        empleado=new Empleado();
    } 
    
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setConsulta(List<Empleado> consulta) {
        this.consulta = consulta;
    }

    public List<Empleado> getConsulta() {
        return consulta;
    }
    
}
