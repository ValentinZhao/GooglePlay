package com.test.zhaoziliang.googleplay.Widget;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.test.zhaoziliang.googleplay.Enum.LoadResult;
import com.test.zhaoziliang.googleplay.R;
import com.test.zhaoziliang.googleplay.Utils.UiUtils;

/**
 * Created by zhaoziliang on 16/11/26.
 */

public abstract class LoadingPage extends FrameLayout{
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

    public LoadingPage(Context context) {
        super(context);
        init();
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        loadingView = createLoadingView();
        if(loadingView != null){
            this.addView(loadingView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }
        emptyView = createEmptyView();
        if(emptyView != null){
            this.addView(emptyView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }
        errorView = createErrorView();
        if(errorView != null){
            this.addView(errorView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
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
                this.addView(successView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                successView.setVisibility(View.VISIBLE);
            }
        }
    }

    private View createErrorView() {
        return View.inflate(UiUtils.getContext(), R.layout.loadpage_error, null);
    }

    private View createEmptyView() {
        return View.inflate(UiUtils.getContext(), R.layout.loadpage_empty, null);
    }

    private View createLoadingView() {
        return View.inflate(UiUtils.getContext(), R.layout.loadpage_loading, null);
    }

    public void show() {
        if(state == STATE_ERROR || state == STATE_EMPTY){
            state = STATE_LOADING;
        }
        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(2000);
                final LoadResult result = load();
                if(UiUtils.getContext() != null){
                    UiUtils.runOnUiThread(new Runnable() {
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

    public abstract View createSuccessView();

    public abstract LoadResult load();
}
