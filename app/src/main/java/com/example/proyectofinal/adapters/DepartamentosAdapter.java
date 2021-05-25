package com.example.proyectofinal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.R;
import com.example.proyectofinal.models.Departamento;

import java.util.List;

public class DepartamentosAdapter extends RecyclerView.Adapter<DepartamentosAdapter.ViewHolderDatos> {

    private List<Departamento> lista;

    public DepartamentosAdapter(List<Departamento> lista){
        this.lista=lista;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_depto, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView txtIdDepartamento;
        TextView txtNombreDepartamnento;

        public ViewHolderDatos(@NonNull View itemView){
            super(itemView);
            txtIdDepartamento=itemView.findViewById(R.id.txtDeptoId);
            txtNombreDepartamnento=itemView.findViewById(R.id.txtDeptoNombre);
        }

        public void asignarDatos(Departamento departamento){
            txtIdDepartamento.setText("ID:"+departamento.getIdDepartamento().toString());
            txtNombreDepartamnento.setText(departamento.getNombre());
        }
    }
}
