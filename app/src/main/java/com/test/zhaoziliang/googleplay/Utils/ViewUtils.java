package com.test.zhaoziliang.googleplay.Utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Created by zhaoziliang on 16/11/23.
 */

public class ViewUtils {
    public static void removeParent(View v){
        ViewParent parent = v.getParent();
        if(parent instanceof ViewGroup){
            ViewGroup viewGroup = (ViewGroup) parent;
            viewGroup.removeView(v);
        }
    }
}
