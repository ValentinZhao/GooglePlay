package com.test.zhaoziliang.googleplay.Utils;

import android.content.res.Resources;

import com.test.zhaoziliang.googleplay.UI.BaseApplication;

/**
 * Created by zhaoziliang on 16/11/23.
 */

public class UiUtils {
    public static Resources getResources(){
        return BaseApplication.getApplication().getResources();
    }

    public static String[] getTabNames(int tabResName){
        return getResources().getStringArray(tabResName);
    }

    public static int dp2px(int dp){
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2dp(int px){
        float scale = getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
