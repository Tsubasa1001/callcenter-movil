package com.callcenter1.callcenter1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.callcenter1.callcenter1.models.DaoSession;
import com.callcenter1.callcenter1.models.Solicitud;

import java.util.ArrayList;
import java.util.List;

public class MisServicios extends AppCompatActivity {

    private ListView misServicios;
    private List<Solicitud> listaSolicitudes;
    private DaoSession session;
    private MiSolicitudAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_servicios);

        misServicios = findViewById(R.id.lv_misServicios);
        listaSolicitudes = new ArrayList<>();
        session = ((CallCenter)(getApplication())).getDaoSession();
        listaSolicitudes = session.getSolicitudDao().loadAll();
        adapter = new MiSolicitudAdapter(getApplicationContext(),(ArrayList<Solicitud>) listaSolicitudes);

        misServicios.setAdapter(adapter);

    }

}
