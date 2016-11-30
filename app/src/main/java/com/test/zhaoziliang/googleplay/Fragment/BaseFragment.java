package com.test.zhaoziliang.googleplay.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.BitmapUtils;
import com.test.zhaoziliang.googleplay.Enum.LoadResult;
import com.test.zhaoziliang.googleplay.Utils.BitmapHelper;
import com.test.zhaoziliang.googleplay.Widget.LoadingPage;

import java.util.List;

/**
 * Created by zhaoziliang on 16/11/26.
 */

public abstract class BaseFragment extends Fragment {
    private LoadingPage loadingPage;
    public BitmapUtils bitmapUtils;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bitmapUtils = BitmapHelper.getBitmapUtils();
        if(loadingPage == null){
            loadingPage = new LoadingPage(getActivity()) {
                @Override
                public View createSuccessView() {
                    return BaseFragment.this.createSuccessView();
                }

                @Override
                public LoadResult load() {
                    return BaseFragment.this.load();
                }
            };
        } else {
            com.test.zhaoziliang.googleplay.Utils.ViewUtils.removeParent(loadingPage);
        }
        return loadingPage;
    }

    public void show(){
        if(loadingPage != null){
            loadingPage.show();
        }
    }

    public LoadResult checkData(List data){
        if(data == null){
            System.out.println("加载数据为空啊!!!!!!");
            return LoadResult.error;
        }
        if(data.size() == 0){
            System.out.println("加载到了数据但没有数据注入");
            return LoadResult.empty;
        } else {
            System.out.println("加载到了数据");
            return LoadResult.success;
        }
    }

    public abstract View createSuccessView();

    public abstract LoadResult load();
}