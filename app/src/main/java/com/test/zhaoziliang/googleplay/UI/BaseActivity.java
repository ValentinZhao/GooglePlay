package com.test.zhaoziliang.googleplay.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhaoziliang on 16/11/22.
 */

public class BaseActivity extends AppCompatActivity {
    private final static List<BaseActivity> mActivities = new LinkedList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initView();
        initActionBar();
    }

    protected void init(){

    }

    protected void initView(){

    }

    protected void initActionBar(){

    }
}
