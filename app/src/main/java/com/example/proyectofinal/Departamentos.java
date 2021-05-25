package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal.interfaces.DepartamentoAPI;
import com.example.proyectofinal.interfaces.EmpleadoAPI;
import com.example.proyectofinal.models.Departamento;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Departamentos extends AppCompatActivity {

    TextView txtIdDepto;
    TextView txtNombreDepto;

    Button btnGuardarDepto;
    Button btnVerTodosDepto;
    Button btnActualizarDepto;
    Button btnEliminarDepto;

    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080/uv/")
            .addConverterFactory(GsonConverterFactory.create()).build();

    DepartamentoAPI departamentoAPI = retrofit.create(DepartamentoAPI.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departamentos);

        txtIdDepto=findViewById(R.id.txtIdDepto);
        txtNombreDepto=findViewById(R.id.txtNombreDepto);

        btnGuardarDepto=findViewById(R.id.btnGuardarDepto);
        btnVerTodosDepto=findViewById(R.id.btnVerTodosDepto);
        btnActualizarDepto=findViewById(R.id.btnActualizarDepto);
        btnEliminarDepto=findViewById(R.id.btnEliminarDepto);



        btnGuardarDepto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Departamento departamento = new Departamento();
                departamento.setNombre(txtNombreDepto.getText().toString().trim());

                guardar(departamento);
            }
        });

        btnEliminarDepto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminar(txtIdDepto.getText().toString().trim());
            }
        });

        btnActualizarDepto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Departamento departamento = new Departamento();
                departamento.setIdDepartamento(Long.parseLong(txtIdDepto.getText().toString().trim()));
                departamento.setNombre(txtNombreDepto.getText().toString().trim());
                actualizar(departamento);
            }
        });

        btnVerTodosDepto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DepartamentosLista.class);
                startActivity(intent);
            }
        });

    }

    private void eliminar(String id) {
        Call<Void> call = departamentoAPI.deleteDepartamento(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(Departamentos.this, "Departamento eliminado", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                    return;
                }
                Toast.makeText(Departamentos.this, "Error al eliminar departamento", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Departamentos.this, "Error al eliminar departamento", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void guardar(Departamento departamento) {
        Call<Departamento> call = departamentoAPI.saveDepartamento(departamento);
        call.enqueue(new Callback<Departamento>() {
            @Override
            public void onResponse(Call<Departamento> call, Response<Departamento> response) {
                if(response.isSuccessful()){
                    Toast.makeText(Departamentos.this, "Departamento guardado", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                    return;
                }
                Toast.makeText(Departamentos.this, "Error al guardar departamento", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Departamento> call, Throwable t) {
                Toast.makeText(Departamentos.this, "Error al guardar departamento", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void actualizar(Departamento departamento){
        Call<Departamento> call = departamentoAPI.updateDepartamento(departamento.getIdDepartamento().toString() ,departamento);
        call.enqueue(new Callback<Departamento>() {
            @Override
            public void onResponse(Call<Departamento> call, Response<Departamento> response) {
                if(response.isSuccessful()){
                    Toast.makeText(Departamentos.this, "Departamento actualizado", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                    return;
                }
                Toast.makeText(Departamentos.this, "Error actualizando", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Departamento> call, Throwable t) {
                Toast.makeText(Departamentos.this, "Error al actualizar documento", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void limpiarCampos(){
        txtIdDepto.setText("");
        txtNombreDepto.setText("");
    }
}