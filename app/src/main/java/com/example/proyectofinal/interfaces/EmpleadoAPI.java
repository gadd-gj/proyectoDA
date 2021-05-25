package com.example.proyectofinal.interfaces;

import com.example.proyectofinal.models.Empleado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmpleadoAPI {
    @POST("empleados")
    Call<Empleado> saveEmpleado(@Body Empleado empleado);
    @GET("empleados")
    Call<List<Empleado>> getAllEmpleados();
    @PUT("empleados/{id}")
    Call<Empleado> updateEmpleado(@Path("id") String id, @Body Empleado empleado);
    @DELETE("empleados/{id}")
    Call<Void> deleteEmpleado(@Path("id") String id);
}
