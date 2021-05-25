package com.example.proyectofinal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.R;
import com.example.proyectofinal.models.Empleado;

import java.util.List;

public class EmpleadosAdapter extends RecyclerView.Adapter<EmpleadosAdapter.ViewHolderDatos> {

    private List<Empleado> lista;

    public EmpleadosAdapter(List<Empleado> lista){
        this.lista=lista;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, null, false);
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

        TextView txtViewNombre;
        TextView txtViewDireccion;
        TextView txtViewTelefono;
        TextView txtId;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            txtViewNombre=itemView.findViewById(R.id.txtViewNombre);
            txtViewDireccion=itemView.findViewById(R.id.txtViewDireccion);
            txtViewTelefono=itemView.findViewById(R.id.txtViewTelefono);
            txtId=itemView.findViewById(R.id.txtId);
        }

        public void asignarDatos(Empleado empleado) {
            txtViewNombre.setText(empleado.getNombre());
            txtViewDireccion.setText(empleado.getDireccion());
            txtViewTelefono.setText(empleado.getTelefono());
            txtId.setText("ID: "+empleado.getId());
        }
    }
}
