package com.callcenter1.callcenter1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.callcenter1.callcenter1.models.Cliente;
import com.callcenter1.callcenter1.models.ClienteDao;
import com.callcenter1.callcenter1.models.DaoSession;
import com.callcenter1.callcenter1.models.Solicitud;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class SolicitudClienteActivity extends AppCompatActivity {

    private RadioGroup radioGroupTipo;
    private RadioButton rBtn_intalacionLTE;
    private RadioButton rBtn_soporteTecnico;
    private RadioButton rBtn_instalacionTelefonia;
    private EditText editTextDetalleTexto;
    private Button btn_enviarSolicitud;
    private DaoSession session;

    private Solicitud solicitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_cliente);

        editTextDetalleTexto = findViewById(R.id.editTextDetalleTexto);
        btn_enviarSolicitud = findViewById(R.id.btn_enviarSolicitud);
        radioGroupTipo = findViewById(R.id.radioGroupTipo);
        session = ((CallCenter)(getApplication())).getDaoSession();

        btn_enviarSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //session.getSolicitudDao().deleteAll();
                solicitud = new Solicitud();///
                solicitud.setDetalle(editTextDetalleTexto.getText().toString());
                int selectID = radioGroupTipo.getCheckedRadioButtonId();
                rBtn_instalacionTelefonia= findViewById(selectID);
                solicitud.setTipoSolicitud(rBtn_instalacionTelefonia.getText().toString());
                solicitud.setDetalle(editTextDetalleTexto.getText().toString());
                solicitud.setEstado("inicio");
                Date curreTime = Calendar.getInstance().getTime();
                solicitud.setHora(String.valueOf(curreTime));
                List<Cliente> clientes = session.getClienteDao().queryBuilder()
                        .where(ClienteDao.Properties.Ci.eq("123456"))
                        .list();
                Cliente client = clientes.get(0);//
                solicitud.setClientId(client.getId());
                long insert = session.getSolicitudDao().insert(solicitud);
                if(insert!=-1){
                    Log.d("insert solicitud", "ok");
                }else{
                    Log.d("insert solicitud", "no insert");
                }

                startActivity(new Intent(SolicitudClienteActivity.this,MisServicios.class));
                sendSolicitud(solicitud);
            }
        });

    }

    public void sendSolicitud(Solicitud solicitud){
        final AsyncHttpClient solicitudhttp = new AsyncHttpClient();
        RequestParams parametros = new RequestParams();
        parametros.put("id",solicitud.getId());
        parametros.put("hora", solicitud.getHora());
        parametros.put("estado", solicitud.getEstado());
        parametros.put("detalle", solicitud.getDetalle());
        parametros.put("tipo", solicitud.getTipoSolicitud());
        parametros.put("cliente", "123456");
        solicitudhttp.post(Config.URL_SERVER+"setsolicitud", parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String respuesta = new String(responseBody);
                Log.d("Datos enviados",respuesta);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String fallo = new String(responseBody);
                Log.d("El fallo fue", fallo);
            }
        });
    }

}
