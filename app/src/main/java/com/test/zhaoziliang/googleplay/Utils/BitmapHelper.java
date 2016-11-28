package com.test.zhaoziliang.googleplay.Utils;

import com.lidroid.xutils.BitmapUtils;

/**
 * Created by zhaoziliang on 16/11/28.
 */

public class BitmapHelper {
    private static BitmapUtils utils;

    public static BitmapUtils getBitmapUtils(){
        utils = new BitmapUtils(UiUtils.getContext(), FileUtils.getIconDir().getAbsolutePath(), 0.3f);
        return utils;
    }
}
