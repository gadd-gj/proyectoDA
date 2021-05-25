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
import org.uv.pojos.Departamento;


/**
 *
 * @author gusky
 */
@ManagedBean
@SessionScoped
public class IndexBeanDepartamentos {
    private Departamento departamento;
    RestClientDepartamentos rest = new RestClientDepartamentos();
    private List<Departamento> consulta;
    
    public IndexBeanDepartamentos() {
        departamento = new Departamento();
    }

    public void ingresar(){
        rest.postJson(departamento);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Nuevo","Registro exitoso"));
        departamento = new Departamento(); 
    }
    public void mostrar(){
        consulta = rest.getJson(consulta);
       
    }
        public void eliminar(){
        rest.deleteJson(departamento.getIdDepartamento());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Eliminar", "Registro eliminado"));
        departamento=new Departamento();
    }
    
    public void actualizar(){
        rest.putJson(departamento, departamento.getIdDepartamento());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Actualizar", "Registro actualizado"));
        departamento=new Departamento();
    } 

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Departamento getDepartamento() {
        return departamento;
    }
    
    

    public void setConsulta(List<Departamento> consulta) {
        this.consulta = consulta;
    }

    public List<Departamento> getConsulta() {
        return consulta;
    }
    
}

