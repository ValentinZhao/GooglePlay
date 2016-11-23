package com.test.zhaoziliang.googleplay.Fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.test.zhaoziliang.googleplay.Enum.LoadResult;
import com.test.zhaoziliang.googleplay.R;

/**
 * Created by zhaoziliang on 16/11/23.
 */

public class HomeFragment extends Fragment {
    private FrameLayout frameLayout;
    private View emptyView;
    private View loadingView;
    private View errorView;
    private View successView;

    public static final int STATE_UNKNOWN = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_EMPTY = 2;
    public static final int STATE_ERROR = 3;
    public static final int STATE_SUCCESS = 4;

    private int state = STATE_UNKNOWN;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(frameLayout == null){
            frameLayout = new FrameLayout(getActivity());
            init();
        } else {
            com.test.zhaoziliang.googleplay.Utils.ViewUtils.removeParent(frameLayout);
        }
        show();//根据服务器状态来显示
        return frameLayout;
    }

    /**
     * 将所有界面:空界面、错误界面等都重叠在FrameLayout中,根据state来选择显示哪个View
     */
    private void init() {
        loadingView = createLoadingView();
        if(loadingView != null){
            frameLayout.addView(loadingView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }
        emptyView = createEmptyView();
        if(emptyView != null){
            frameLayout.addView(emptyView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }
        errorView = createErrorView();
        if(errorView != null){
            frameLayout.addView(errorView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }
        showPageByState();
    }

    private void showPageByState() {
        if(loadingView != null){
            loadingView.setVisibility(state == STATE_UNKNOWN || state == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        }
        if (emptyView != null){
            emptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
        }
        if (errorView != null){
            errorView.setVisibility(state == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        }
        if(state == STATE_SUCCESS){
            successView = createSuccessView();
            if(successView != null){
                frameLayout.addView(successView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                successView.setVisibility(View.VISIBLE);
            }
        }
    }

    private View createSuccessView() {
        TextView tv = new TextView(getActivity());
        tv.setText("Loading Complete!");
        tv.setTextSize(28);
        return tv;
    }

    private View createErrorView() {
        return View.inflate(getActivity(), R.layout.loadpage_error, null);
    }

    private View createEmptyView() {
        return View.inflate(getActivity(), R.layout.loadpage_empty, null);
    }

    private View createLoadingView() {
        return View.inflate(getActivity(), R.layout.loadpage_loading, null);
    }

    private void show() {
        if(state == STATE_ERROR || state == STATE_EMPTY){
            state = STATE_LOADING;
        }
        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(2000);
                final LoadResult result = load();
                if(getActivity() != null){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            state = result.getValue();
                            showPageByState();
                        }
                    });
                }
            }
        }.start();
        showPageByState();
    }

    private LoadResult load() {
        return LoadResult.success;
    }
}
