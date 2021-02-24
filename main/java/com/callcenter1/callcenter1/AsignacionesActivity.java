package com.callcenter1.callcenter1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.callcenter1.callcenter1.models.Asignacion;
import com.callcenter1.callcenter1.models.DaoSession;
import com.callcenter1.callcenter1.models.Empleado;
import com.callcenter1.callcenter1.models.Solicitud;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class AsignacionesActivity extends BaseActivity implements View.OnClickListener {
    //private DaoSession session;
    private ListView listView_listaTrabajos;
    private List<Asignacion> listaAsignaciones;
    private ItemAsignacionesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignaciones);

        listaAsignaciones = new ArrayList<>();
        cargarDatosBD();
        Bundle bundle = getIntent().getExtras();
        int ci = bundle.getInt("ci");
        getAsignaciones(String.valueOf(ci));

        listView_listaTrabajos = findViewById(R.id.lv_lista_asignaciones);

        adapter = new ItemAsignacionesAdapter(this,(ArrayList<Asignacion>) listaAsignaciones, session);
        listView_listaTrabajos.setAdapter(adapter);

        listView_listaTrabajos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //bundle es para enviar parametros de un activity a otro
                Bundle paramentros = new Bundle();

                paramentros.putLong("id", listaAsignaciones.get(i).getId());
                Intent intent = new Intent(getBaseContext(), DetalleItemAsignacionActivity.class);
                intent.putExtras(paramentros);
                startActivity(intent);
            }
        });

    }

    @Override
    public void loadView() {

    }

    @Override
    public void initBD() {

    }

    private void cargarDatosBD(){
        listaAsignaciones = session.getAsignacionDao().loadAll();
    }

    private void getAsignaciones(String ci){
        AsyncHttpClient clientHttp = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("ci",ci);
        clientHttp.post(Config.URL_SERVER + "listarasignaciones",params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String respuesta  = new String(responseBody);
            //    Log.d("asignaciones", respuesta);
                try {
                    JSONObject object = new JSONObject(respuesta);
                    String  value =  object.getString("asignacion");
                    String  value2 = object.getString("solicitudes");

                    JSONArray arraySolicitudes = new JSONArray(value);
                    JSONArray arraySolicitudes2 = new JSONArray(value2);

                    for (int i = 0; i < arraySolicitudes.length() ; i++) {
                        long idm = Long.valueOf(arraySolicitudes2.getInt(i));
                     //   saveSolicitud(idm);
                        Log.d("solicitus_id", String.valueOf(idm));
                        long id  = arraySolicitudes.getJSONObject(i).getInt("pk");
                        JSONObject object1 = arraySolicitudes.getJSONObject(i).getJSONObject("fields");
                       Asignacion asignacion =  parserAsignacion(object1,  idm, id);
                       //asignacion.setSolicitudId();
                       long result = session.getAsignacionDao().insertOrReplace(asignacion);
                       if(result!=-1){
                           Log.d("SAve", "ok");
                       }else{
                           Log.d("Error", "error");
                       }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("error en la lista ", new String(responseBody));
            }
        });
    }


    private Asignacion parserAsignacion(JSONObject object, long idm, long id){
        Asignacion asignacion = new Asignacion();
        try {
            asignacion.setId(id) ;
            asignacion.setHoraInicio(object.getString("hora_inicio"));
            asignacion.setHoraFin(object.getString("hora_fin"));
            asignacion.setEmpleadoId(Long.valueOf(object.getInt("empleado")));
            asignacion.setSolicitudId(idm);
            asignacion.setMateriales(String.valueOf(object.getInt("material")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return asignacion;
    }

    public void saveSolicitud(final long idm){
        AsyncHttpClient solicitudtHttp = new AsyncHttpClient();

        solicitudtHttp.get(Config.URL_SERVER + "solicitudes", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String respuesta  = new String(responseBody);
                //    Log.d("asignaciones", respuesta);
                try {
                    JSONObject object = new JSONObject(respuesta);
                    String  value =  object.getString("solicitud");

                    JSONArray arraySolicitudes = new JSONArray(value);
            

                    for (int i = 0; i < arraySolicitudes.length() ; i++) {
                        int id  = arraySolicitudes.getJSONObject(i).getInt("pk");

                        JSONObject object1 = arraySolicitudes.getJSONObject(i).getJSONObject("fields");
                        if(((int) idm)==object1.getInt("idm")){
                            Solicitud solicitud =  new Solicitud();
                            solicitud.parseSolicitud(id,object1);
                            long result = session.getSolicitudDao().insertOrReplace(solicitud);
                            if(result!=-1){
                                Log.d("SAve", "solicitud");
                            }else{
                                Log.d("Error", "error");
                            }
                        }
                    }
                    // Log.d("value", arrayASinganciones.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("error en la lista ", new String(responseBody));
            }
        });

    }


    @Override
    public void onClick(View view) {

    }
}


