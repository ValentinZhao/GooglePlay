package com.test.zhaoziliang.googleplay.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zhaoziliang on 16/11/22.
 */

public class BaseActivity extends AppCompatActivity {
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
