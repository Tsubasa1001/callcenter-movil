package com.callcenter1.callcenter1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.callcenter1.callcenter1.models.Cliente;
import com.callcenter1.callcenter1.models.ClienteDao;
import com.callcenter1.callcenter1.models.Empleado;
import com.callcenter1.callcenter1.models.EmpleadoDao;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.greenrobot.greendao.query.QueryBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btnLogin;
    private EditText editUser;
    private EditText editPassword;
    private TextView txtViewRegistrarse;

    private String user;
    private String password;

    private Cliente cliente;
    private Empleado empleado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cliente = new Cliente();
        empleado = new Empleado();

        getCliente();
        getEmpleado();
        loadView();
        loadData();

        btnLogin.setOnClickListener(this);
        txtViewRegistrarse.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(checkNetworkConnection()) {
            getCliente();
        }else{
            errorUser("No tiene conexion a internet");
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("activity", "Destruida");
    }

    private void loadData(){

    }

    public void getEmpleado(){
        final AsyncHttpClient empleadoService = new AsyncHttpClient();
        empleadoService.get(Config.URL_SERVER+"empleados", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                String respuesta = new String(response);
                ArrayList<String> listaA = new ArrayList<>();
                try {
                    JSONObject object = new JSONObject(respuesta);

                    String empleadostr = object.getString("empleado");

                    JSONArray array= new JSONArray(empleadostr);

                    for (int i = 0; i < array.length() ; i++) {
                        int id= array.getJSONObject(i).getInt("pk");
                        Empleado empleado = new Empleado();
                        empleado.parseEmpleado(id,array.getJSONObject(i).getJSONObject("fields"));

                        guardaOReemplazarEmpleadoBD(empleado);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("Respuesta",respuesta);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                String error = new String(errorResponse);
                Log.d("ERROR","error");
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }

    public void getCliente(){
        final AsyncHttpClient client = new AsyncHttpClient();
        client.get(Config.URL_SERVER+"clientes", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                String respuesta = new String(response);
                ArrayList<String> listaA = new ArrayList<>();
                try {
                    JSONObject object = new JSONObject(respuesta);

                    String clientestr = object.getString("clientes");

                    JSONArray array= new JSONArray(clientestr);

                    for (int i = 0; i < array.length() ; i++) {
                        int id= array.getJSONObject(i).getInt("pk");
                        Cliente cliente = new Cliente();
                        cliente.parseCliente(id,array.getJSONObject(i).getJSONObject("fields"));
                        guardaOReemplazarCLientesBD(cliente);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("Respuesta",respuesta);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                String error = new String(errorResponse);
                Log.d("ERROR","error");
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }


    private boolean loginCliente(final String username, final String password){
        final boolean valido = true;
        if(username !=null  && password !=null ){
            AsyncHttpClient clientHttp = new AsyncHttpClient();
            RequestParams paramns = new RequestParams();
            paramns.put("username", username);
            paramns.put("password", password);
            clientHttp.post(Config.URL_SERVER + "login", paramns, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String respuesta = new String(responseBody);
                    Log.d("respuesta login ", respuesta);
                    JSONObject object = null;
                    try {
                        object = new JSONObject(respuesta);
                        if(object.get("result") == "valido"){
                           // cliente = new Cliente();
                        }else{
                              // invalido
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFinish() {
                    super.onFinish();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Log.d("Error login ", new String(responseBody));
                }
            });
        }
        return valido;
    }

    @Override
    public void loadView() {
        btnLogin = findViewById(R.id.btn_login);
        editUser = findViewById(R.id.edituser);
        editPassword = findViewById(R.id.editpassword);
        txtViewRegistrarse = findViewById(R.id.txtView_registrarse);
    }

    @Override
    public void initBD() {


    }

    public  void guardaOReemplazarEmpleadoBD(Empleado empleado){
        session.getEmpleadoDao().insertOrReplace(empleado);
//        List<Empleado> empleados = session.getEmpleadoDao().queryBuilder()
//                .where(EmpleadoDao.Properties.Ci.eq(empleado.getCi()))
//                .list();
//        if(empleados.size()==0){
//            // cliente.setCi(123);
//            // cliente.setLatitud(0.0);
//            //  cliente.setLongitud(0.0);
//            session.getEmpleadoDao().insertOrReplace(empleado);
//            // session.getClienteDao().insertOrReplaceInTx(cliente);
//        }else{
//            Log.d("EXiste", "Valor existe");
//
//        }
    }

    public  void guardaOReemplazarCLientesBD(Cliente cliente){
        List<Cliente> clientes = session.getClienteDao().queryBuilder()
                .where(ClienteDao.Properties.Ci.eq(cliente.getCi()))
                .list();
        if(clientes.size()==0){
          // cliente.setCi(123);
          // cliente.setLatitud(0.0);
          //  cliente.setLongitud(0.0);
            session.getClienteDao().insertOrReplace(cliente);
           // session.getClienteDao().insertOrReplaceInTx(cliente);
        }else{
            Log.d("EXiste", "Valor existe");

        }
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case(R.id.btn_login):
                iniciarSesssion();
                break;
            case(R.id.txtView_registrarse):
                startActivity(new Intent(MainActivity.this,TipoUsuario.class));
                break;
        }
    }

    public void iniciarSesssion(){

//        Bundle bundle = getIntent().getExtras();
//        long id = bundle.getLong("id");

        user = editUser.getText().toString();
        password = editPassword.getText().toString();

        cliente = obtenerDatosCliente(user,password);
        empleado = obtenerDatosEmpleado(user,Integer.parseInt(password));

        if(cliente!=null) {
            if ((!user.equals("")) && (!password.equals(""))) {
                if ((user.equals(cliente.getNombre())) && (password.equals(cliente.getNroContrato()))) {
                    startActivity(new Intent(MainActivity.this, SolicitudServicioOReclamoActivity.class));

                } else {
                    errorUser("Usuario o contraseña invalidos");

                }
            } else {
                Toast.makeText(getBaseContext(), R.string.msg_login, Toast.LENGTH_LONG).show();
            }
        }else{
            if ((user.equals(empleado.getNombre())) && (password.equals(String.valueOf(empleado.getCodIdentificacion())))) {
                Intent intent = new Intent(MainActivity.this, AsignacionesActivity.class);
                Bundle paramentros = new Bundle();
                paramentros.putInt("ci", empleado.getCi());
                intent.putExtras(paramentros);
                startActivity(intent);

            } else {
                errorUser("Usuario o contraseña invalidos");
            }

        }
    }

    private Cliente obtenerDatosCliente(String nombre,String NroContrato){
        List<Cliente> clientes = new ArrayList<>(); // = session.getClienteDao();
        QueryBuilder qb = session.getClienteDao().queryBuilder();
        qb.where(ClienteDao.Properties.Nombre.eq(nombre),
                qb.and(ClienteDao.Properties.NroContrato.eq(NroContrato),ClienteDao.Properties.Nombre.eq(nombre)));
        clientes = qb.list();
        if(clientes.size()!=0){
            return clientes.get(0);
        }
        return null;
    }

    private Empleado obtenerDatosEmpleado(String nombre,int codIdentificacion){
        List<Empleado> empleados= new ArrayList<>();
                QueryBuilder qb = session.getEmpleadoDao().queryBuilder();
        qb.where(EmpleadoDao.Properties.Nombre.eq(nombre),
                qb.and(EmpleadoDao.Properties.CodIdentificacion.eq(codIdentificacion),EmpleadoDao.Properties.Nombre.eq(nombre)));
        empleados = qb.list();
        if(empleados.size()!=0){
            return empleados.get(0);
        }
        return null;
    }



}



