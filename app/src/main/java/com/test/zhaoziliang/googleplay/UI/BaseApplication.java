package com.test.zhaoziliang.googleplay.UI;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhaoziliang on 16/11/23.
 */

public class BaseApplication extends Application {
    private static BaseApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Context getApplication() {
        return application;
    }
}
