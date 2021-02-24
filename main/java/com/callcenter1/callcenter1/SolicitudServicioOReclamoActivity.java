package com.callcenter1.callcenter1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class SolicitudServicioOReclamoActivity extends AppCompatActivity {

    private Button btnServicio;
    private Button btnReclamo;
    private Button misServicios;
    private Button misReclamos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_servicio_oreclamo);
         btnServicio = findViewById(R.id.btn_servicio);
        btnReclamo = findViewById(R.id.btn_reclamo);
        misServicios = findViewById(R.id.btn_misServicios);
        misReclamos = findViewById(R.id.btn_misReclamos);


        btnReclamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SolicitudServicioOReclamoActivity.this,TipoReclamoActivity.class));
            }
        });

        btnServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SolicitudServicioOReclamoActivity.this,SolicitudClienteActivity.class));
            }
        });



        misServicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SolicitudServicioOReclamoActivity.this,MisServicios.class));
            }
        });

    }


}
