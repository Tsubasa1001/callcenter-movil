package com.callcenter1.callcenter1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.callcenter1.callcenter1.models.Solicitud;

import java.util.ArrayList;

public class MiSolicitudAdapter extends ArrayAdapter<Solicitud> {

    ArrayList<Solicitud> listaSolicitudes = new ArrayList<>();

    public MiSolicitudAdapter(@NonNull Context context,  ArrayList<Solicitud> solicitudes) {
        super(context,0, solicitudes);

        this.listaSolicitudes= solicitudes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Solicitud solicitud= listaSolicitudes.get(position) ;
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_solicitud,parent,false);

        }
        TextView tipo = convertView.findViewById(R.id.tvTipoSolicitud);
        TextView hora = convertView.findViewById(R.id.tvHora);
        TextView detalle = convertView.findViewById(R.id.tvsolicitudDetalle);
        TextView estado = convertView.findViewById(R.id.tvEstado);
        tipo.setText(solicitud.getTipoSolicitud());
        hora.setText(solicitud.getHora());
        detalle.setText(solicitud.getDetalle());
        estado.setText(solicitud.getEstado());
        return convertView;


    }
}
