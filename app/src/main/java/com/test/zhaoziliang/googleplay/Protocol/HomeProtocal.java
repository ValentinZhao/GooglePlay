package com.test.zhaoziliang.googleplay.Protocol;

import com.test.zhaoziliang.googleplay.Model.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoziliang on 16/11/27.
 */

public class HomeProtocal extends BaseProtocal<List<AppInfo>> {

    @Override
    public List<AppInfo> parseJson(String json) {
        List<AppInfo> appInfos = new ArrayList<>();
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
                appInfos.add(info);
            }
            return appInfos;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
