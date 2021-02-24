package com.callcenter1.callcenter1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.callcenter1.callcenter1.models.Asignacion;
import com.callcenter1.callcenter1.models.Cliente;
import com.callcenter1.callcenter1.models.ClienteDao;
import com.callcenter1.callcenter1.models.DaoSession;
import com.callcenter1.callcenter1.models.Solicitud;
import com.callcenter1.callcenter1.models.SolicitudDao;

import java.util.ArrayList;
import java.util.List;

public class ItemAsignacionesAdapter extends ArrayAdapter<Asignacion> {
    ArrayList<Asignacion> listaAsignaciones = new ArrayList<>();

    private DaoSession session;

    public ItemAsignacionesAdapter(@NonNull Context context, ArrayList<Asignacion> asignaciones, DaoSession session) {
        super(context,0, asignaciones);

        this.listaAsignaciones = asignaciones;
        this.session = session;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Asignacion asignacion= listaAsignaciones.get(position) ;
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_asignacion,parent,false);

        }
        TextView tipo = convertView.findViewById(R.id.tvTipoSolicitud);
        TextView hora = convertView.findViewById(R.id.tvHora);
        TextView detalle = convertView.findViewById(R.id.tvsolicitudDetalle);
        TextView estado = convertView.findViewById(R.id.tvEstado);

        //asignacion.getSolicitudId();
        Solicitud solicitud = getSolicitud(asignacion.getSolicitudId());
        tipo.setText(solicitud.getTipoSolicitud());
        hora.setText(asignacion.getHoraInicio());
        detalle.setText(asignacion.getMateriales() + "- Cliente" + solicitud.getClientId());
        estado.setText(solicitud.getEstado());
        return convertView;


    }

    public Solicitud getSolicitud(long solicitudId){
        Solicitud solicitud= new Solicitud();
        List<Solicitud> solicitudes = session.getSolicitudDao().queryBuilder()
                .where(SolicitudDao.Properties.Id.eq(solicitudId))
                .list();
        if(solicitudes.size()>0){
            solicitud = solicitudes.get(0);
            return solicitud;
        }

        return solicitud;
    }
}
