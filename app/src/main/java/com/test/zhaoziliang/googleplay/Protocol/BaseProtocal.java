package com.test.zhaoziliang.googleplay.Protocol;

import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.test.zhaoziliang.googleplay.Utils.UiUtils;

/**
 * Created by zhaoziliang on 16/11/26.
 */

public abstract class BaseProtocal<T> {
    public static final String SERVER_URL = "http://172.27.104.125/WebInfos";

    public static final String APP_URL = SERVER_URL + "/app/applist";

    private String result;

    public T load(int index){
        //先加载本地数据
        String json = loadLocal(index);
        if(json == null){
            json = loadServer(index);
        } else {
            saveLocal(json, index);
        }
        if(json != null){
            return parseJson(json);
        } else {
            return null;
        }
    }

    private String loadLocal(int index) {
        return null;
    }

    private String loadServer(int index) {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, APP_URL + index, new RequestCallBack<String>(){

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                result = (String) responseInfo.result;
                System.out.println("APP详情页返回结果:" + result);
                Toast.makeText(UiUtils.getContext(), "加载成功!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(UiUtils.getContext(), "加载失败!", Toast.LENGTH_SHORT).show();
            }
        });

        return result;
    }

    private void saveLocal(String json, int index) {

    }

    public abstract T parseJson(String json);

}
