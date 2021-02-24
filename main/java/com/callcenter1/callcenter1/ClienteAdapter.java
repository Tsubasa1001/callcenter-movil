package com.callcenter1.callcenter1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.callcenter1.callcenter1.models.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteAdapter extends ArrayAdapter<Cliente> {

    public ClienteAdapter(Context context, ArrayList<Cliente> clientes) {
        super(context,0, clientes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Cliente cliente = getItem(position);
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_item_cliente,parent,false);

        }
        TextView tvNombre = convertView.findViewById(R.id.tvNombre);
        TextView tvDireccion = convertView.findViewById(R.id.tvdireccion);
        tvNombre.setText(cliente.nombre);
        tvDireccion.setText(cliente.direccion);
        return convertView;
    }


}
