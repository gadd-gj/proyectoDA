package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal.interfaces.EmpleadoAPI;
import com.example.proyectofinal.models.Empleado;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{

    TextView txtIdEmpleado;
    TextView txtNombre;
    TextView txtDireccion;
    TextView txtTelefono;
    TextView txtDepartamento;

    Button btnGuardar;
    Button btnVerTodos;
    Button btnActualizar;
    Button btnEliminar;
    Button btnDepartamentos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Conectar a la vista
        txtIdEmpleado=findViewById(R.id.txtIdEmpleado);
        txtNombre=findViewById(R.id.txtNombre);
        txtDireccion=findViewById(R.id.txtDireccion);
        txtTelefono=findViewById(R.id.txtTelefono);
        txtDepartamento=findViewById(R.id.txtDepartamento);

        btnGuardar=findViewById(R.id.btnGuardar);
        btnVerTodos=findViewById(R.id.btnVerTodos);
        btnDepartamentos=findViewById(R.id.btnDepartamentos);
        btnActualizar=findViewById(R.id.btnActualizar);
        btnEliminar=findViewById(R.id.btnEliminar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear objeto
                Empleado empleado = new Empleado();
                empleado.setNombre(txtNombre.getText().toString().trim());
                empleado.setDireccion(txtDireccion.getText().toString().trim());
                empleado.setTelefono(txtTelefono.getText().toString().trim());
                empleado.setIdDepartamento(Long.parseLong(txtDepartamento.getText().toString().trim()));
//                Toast.makeText(MainActivity.this, empleado.getDepartamento().toString(), Toast.LENGTH_SHORT).show();
                guardar(empleado);

            }
        });

        btnVerTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EmpleadosLista.class);
                startActivity(intent);
            }
        });

        btnDepartamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Departamentos.class);
                startActivity(intent);
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear objeto
                Empleado empleado = new Empleado();
                empleado.setId(Long.parseLong(txtIdEmpleado.getText().toString().trim()));
                empleado.setIdEmpleado(Long.parseLong(txtIdEmpleado.getText().toString().trim()));
                empleado.setNombre(txtNombre.getText().toString().trim());
                empleado.setDireccion(txtDireccion.getText().toString().trim());
                empleado.setTelefono(txtTelefono.getText().toString().trim());
                empleado.setIdDepartamento(Long.parseLong(txtDepartamento.getText().toString().trim()));

                actualizar(empleado);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminar(txtIdEmpleado.getText().toString());
            }
        });
    }

    private void eliminar(String id) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.65:8080/uv/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        EmpleadoAPI empleadoAPI = retrofit.create(EmpleadoAPI.class);

        Call<Void> call = empleadoAPI.deleteEmpleado(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                    return;
                }
                Toast.makeText(MainActivity.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error al eliminar usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void actualizar(Empleado empleado) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.65:8080/uv/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        EmpleadoAPI empleadoAPI = retrofit.create(EmpleadoAPI.class);

        Call<Empleado> call = empleadoAPI.updateEmpleado(empleado.getId().toString(),empleado);

        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                String code = response.toString();
                System.out.println(code);
                if(response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Actualizado", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                }
                else
                    Toast.makeText(MainActivity.this, "Ocurrio un error al atualizar" , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error al actualizar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void guardar(Empleado empleado){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.65:8080/uv/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        EmpleadoAPI empleadoAPI = retrofit.create(EmpleadoAPI.class);

        Call<Empleado> call = empleadoAPI.saveEmpleado(empleado);
        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                String code = response.toString();
                System.out.println(code);
                if(response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Guardado", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                }
                else
                    Toast.makeText(MainActivity.this, "Ocurrio un error al guardar" , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error al guardar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void limpiarCampos(){
        txtIdEmpleado.setText("");
        txtNombre.setText("");
        txtDepartamento.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
    }
}