package com.callcenter1.callcenter1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.callcenter1.callcenter1.models.DaoSession;

public abstract class BaseActivity extends AppCompatActivity {
    public DaoSession session;

    //metodos

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = ((CallCenter)(getApplication())).getDaoSession();
    }

    public abstract void loadView();
    public abstract void initBD();

    public boolean checkNetworkConnection() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        boolean isConnected = false;
        if (networkInfo != null && (isConnected = networkInfo.isConnected())) {
            isConnected = true;
        }
        return isConnected;
    }

    public void errorUser(String mensaje){
        Toast.makeText(getBaseContext(), mensaje, Toast.LENGTH_LONG).show();
    }

}
