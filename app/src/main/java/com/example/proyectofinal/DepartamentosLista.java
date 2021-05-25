package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.proyectofinal.adapters.DepartamentosAdapter;
import com.example.proyectofinal.interfaces.DepartamentoAPI;
import com.example.proyectofinal.models.Departamento;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DepartamentosLista extends AppCompatActivity {
    private List<Departamento> departamentos;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departamentos_lista);
        
        recyclerView=findViewById(R.id.departamentosRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        
        obtenerDepartamentos();
    }
    
    private void obtenerDepartamentos(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.65:8080/uv/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        DepartamentoAPI departamentoAPI = retrofit.create(DepartamentoAPI.class);
        
        Call<List<Departamento>> call = departamentoAPI.getAllDepartametos();
        call.enqueue(new Callback<List<Departamento>>() {
            @Override
            public void onResponse(Call<List<Departamento>> call, Response<List<Departamento>> response) {
                if(response.isSuccessful()){
                    departamentos = response.body();
                    DepartamentosAdapter departamentosAdapter = new DepartamentosAdapter(departamentos);
                    recyclerView.setAdapter(departamentosAdapter);
                    return;
                }
                Toast.makeText(DepartamentosLista.this, "Error al cargar departamentos", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Departamento>> call, Throwable t) {
                Toast.makeText(DepartamentosLista.this, "Error al cargar departamentos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}