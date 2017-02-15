package com.example.shaorui.androidarchitectureevolution;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * Created by shaorui on 17/2/15.
 */
public class MyApplication extends Application {
    private static Context sContext;

    //FIXME Provider中不能使用该方法
    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();

        //for chrome inspect
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());

    }
}
