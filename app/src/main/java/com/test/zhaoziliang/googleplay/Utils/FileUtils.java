package com.test.zhaoziliang.googleplay.Utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by zhaoziliang on 16/11/26.
 */

public class FileUtils {
    public static final String ICON = "icon";

    public static final String CACHE = "cache";

    public static final String ROOT = "GooglePlay";

    public static File getIconDir(){
        return getDir(ICON);
    }

    public static File getCacheDir(){
        return getDir(CACHE);
    }

    private static File getDir(String icon) {
        StringBuilder path = new StringBuilder();
        if(isSDCardAvailable()){ //SD卡可用的时候
            path.append(Environment.getExternalStorageDirectory());
            path.append(File.separator);
            path.append(ROOT);
            path.append(File.separator);
            path.append(icon);
        } else { //SD卡不可用的时候我们直接用内存来缓存
            path.append(UiUtils.getContext().getCacheDir().getAbsolutePath());//  data/data.com.test.zhaoziliang.googleplay/cache
            path.append(File.separator);//  data/data.com.test.zhaoziliang.googleplay/cache/
            path.append(icon);
        }
        File file = new File(path.toString());
        if(!file.exists() || !file.isDirectory()){
            file.mkdirs();
        }
        return file;
    }

    public static boolean isSDCardAvailable(){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return true;
        } else {
            return false;
        }
    }
}
