package com.callcenter1.callcenter1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;

public class TipoReclamoActivity extends AppCompatActivity {
        private RadioButton rBtnCableado_estructural;
        private RadioButton rBtnInstalacion_LTE;
        private RadioButton rBtnTelefonia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_reclamo);
        rBtnCableado_estructural = findViewById(R.id.rBtnCableado_estructural);
        rBtnInstalacion_LTE = findViewById(R.id.rBtnInstalacion_LTE);
    }
}
