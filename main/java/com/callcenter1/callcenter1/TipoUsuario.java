package com.callcenter1.callcenter1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TipoUsuario extends AppCompatActivity {

    private Button btnTipoTrabajador;
    private Button btnTipoCliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_usuario);

        btnTipoTrabajador = findViewById(R.id.btn_tipoTrabajador);
        btnTipoCliente = findViewById(R.id.btn_tipoCliente);


        btnTipoTrabajador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TipoUsuario.this,RegistroTrabajadorActivity.class));
            }
        });

        btnTipoCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TipoUsuario.this,RegistroClienteActivity.class));
            }
        });
    }



}
