package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.proyectofinal.adapters.EmpleadosAdapter;
import com.example.proyectofinal.interfaces.EmpleadoAPI;
import com.example.proyectofinal.models.Empleado;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmpleadosLista extends AppCompatActivity {
    private List<Empleado> empleados;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados_lista);

        recyclerView=findViewById(R.id.empleadosRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));

        obtenerEmpleados();


    }

    private void obtenerEmpleados(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.65:8080/uv/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        EmpleadoAPI empleadoAPI = retrofit.create(EmpleadoAPI.class);

        Call<List<Empleado>> call = empleadoAPI.getAllEmpleados();
        call.enqueue(new Callback<List<Empleado>>() {
            @Override
            public void onResponse(Call<List<Empleado>> call, Response<List<Empleado>> response) {
                if(response.isSuccessful()){
                    empleados = response.body();
                    System.out.println("Cargado...");


                    EmpleadosAdapter empleadosAdapter = new EmpleadosAdapter(empleados);
                    recyclerView.setAdapter(empleadosAdapter);
                    return;
                }
                Toast.makeText(EmpleadosLista.this, "Error al obtener los empleados", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Empleado>> call, Throwable t) {
                Toast.makeText(EmpleadosLista.this, "Error al obtener los empleados", Toast.LENGTH_SHORT).show();
            }
        });
    }
}