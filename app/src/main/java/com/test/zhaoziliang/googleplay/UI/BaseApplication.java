package com.test.zhaoziliang.googleplay.UI;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by zhaoziliang on 16/11/23.
 */

public class BaseApplication extends Application {
    private static BaseApplication application;
    private static int mainTid;
    private static Handler handler;
    @Override
//  在主线程运行的
    public void onCreate() {
        super.onCreate();
        application=this;
        mainTid = android.os.Process.myTid();
        handler=new Handler();
    }
    public static Context getApplication() {
        return application;
    }
    public static int getMainTid() {
        return mainTid;
    }
    public static Handler getHandler() {
        return handler;
    }


}
