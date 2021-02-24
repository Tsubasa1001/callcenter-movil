package com.callcenter1.callcenter1;
import org.greenrobot.greendao.database.Database;
import android.app.Application;

import com.callcenter1.callcenter1.models.DaoMaster;
import com.callcenter1.callcenter1.models.DaoSession;


public class CallCenter extends Application {

    private DaoSession daosession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "callCenter.db");
        Database db = helper.getWritableDb();
        daosession = new DaoMaster(db).newSession();

    }
    public DaoSession getDaoSession(){
        return daosession;

    }
}
