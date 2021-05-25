package com.example.proyectofinal.interfaces;

import com.example.proyectofinal.models.Departamento;
import com.example.proyectofinal.models.Empleado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DepartamentoAPI {
    @POST("departamentos")
    Call<Departamento> saveDepartamento(@Body Departamento departamento);
    @GET("departamentos")
    Call<List<Departamento>> getAllDepartametos();
    @PUT("departamentos/{id}")
    Call<Departamento> updateDepartamento(@Path("id") String id, @Body Departamento departamento);
    @DELETE("departamentos/{id}")
    Call<Void> deleteDepartamento(@Path("id") String id);
}
