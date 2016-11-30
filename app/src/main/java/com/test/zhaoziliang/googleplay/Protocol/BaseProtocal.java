package com.test.zhaoziliang.googleplay.Protocol;

import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.test.zhaoziliang.googleplay.Model.AppInfo;
import com.test.zhaoziliang.googleplay.Utils.UiUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoziliang on 16/11/26.
 */

public abstract class BaseProtocal<T> {
    public static final String SERVER_URL = "http://192.168.1.106:8080/WebInfos";

    public static final String APP_URL = SERVER_URL + "/app/applist";

    public List<AppInfo> loadResult = new ArrayList<>();


    public List<AppInfo> load(int index){
        //先加载本地数据
        String json = loadLocal(index);
        loadServer(index);
        json = loadResult.toString();
        if(loadResult != null){
            System.out.println("成功parse" + json);
            return loadResult;
        } else {
            return null;
        }
    }

    private String loadLocal(int index) {
        return null;
    }

    private void loadServer(int index) {
//        System.out.println("server loaded!");
        HttpUtils utils = new HttpUtils();
        utils.send(HttpMethod.GET, APP_URL + index, new RequestCallBack<String>(){

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = (String) responseInfo.result;
                System.out.println("result:" + result);
                parseJson(result);
                Toast.makeText(UiUtils.getContext(), "加载成功!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(UiUtils.getContext(), "加载失败!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveLocal(String json, int index) {

    }

    public void parseJson(String json){
        List<AppInfo> appInfos = new ArrayList<>();
        System.out.println("json:" + json);
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);
                long id = object.getLong("id");
                String name = object.getString("name");
                String packageName = object.getString("packageName");
                String iconUrl = object.getString("iconUrl");
                float stars = Float.parseFloat(object.getString("stars"));
                long size = object.getLong("size");
                String downloadUrl = object.getString("downloadUrl");
                String des = object.getString("des");
                AppInfo info = new AppInfo(id, name, packageName, iconUrl, stars, size, downloadUrl, des);
                System.out.println("name:" + name);
                System.out.println("iconUrl:" + iconUrl);
                System.out.println("downloadUrl:" + downloadUrl);
                appInfos.add(info);
            }
            loadResult = appInfos;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
