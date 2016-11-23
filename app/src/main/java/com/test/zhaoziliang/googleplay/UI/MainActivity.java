package com.test.zhaoziliang.googleplay.UI;

import android.os.Build;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.test.zhaoziliang.googleplay.Adapter.MainFragmentPagerAdapter;
import com.test.zhaoziliang.googleplay.R;

public class MainActivity extends BaseActivity implements SearchView.OnQueryTextListener{
    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;
    private PagerTabStrip mPagerTabStrip;
    private MainFragmentPagerAdapter pagerAdapter;
    private ActionBarDrawerToggle toggle;
    private String[] tab_names;


    @Override
    protected void init() {
        tab_names = getResources().getStringArray(R.array.tab_names);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mPagerTabStrip = (PagerTabStrip) findViewById(R.id.tab_strip);
        pagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), tab_names);
        mViewPager.setAdapter(pagerAdapter);
    }

    @Override
    protected void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(MainActivity.this, "抽屉打开了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(MainActivity.this, "抽屉关上了", Toast.LENGTH_SHORT).show();
            }
        };
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();//将toggle与Actionbar建立起联系
        actionBar.setHomeAsUpIndicator(R.drawable.ic_launcher);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        if (Build.VERSION.SDK_INT > 11){
            SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
            searchView.setOnQueryTextListener(this);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_search){
            Toast.makeText(getApplicationContext(), "搜索", Toast.LENGTH_SHORT).show();
        }
        return toggle.onOptionsItemSelected(item) | super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }
}
