package com.larry.present.boot;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.Toast;

import com.larry.present.R;
import com.larry.present.account.AccountManager;
import com.larry.present.boot.adapter.BaseFragmentPagerAdapter;
import com.larry.present.sign.fragment.StudentSigntFragment;
import com.larry.present.sign.fragment.TeacherCheckSignFragment;
import com.larry.present.sign.fragment.TeacherSignFragment;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.app_bar_main_tl)
    TabLayout appBarMainTl;

    @BindView(R.id.activity_main_viewpager_vp)
    ViewPager activityMainViewpagerVp;

    FragmentPagerAdapter mFragmentPagerAdapter;

    /**
     * fragment集合
     */
    List<Fragment> mFragmentList;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    RxPermissions rxPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initConfig();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public void initView() {
        setSupportActionBar(toolbar);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mFragmentList = new ArrayList<Fragment>();
        //如果当前是学生实例登录存在
        if (AccountManager.getStudent() != null) {
            mFragmentList.add(new StudentSigntFragment());
        }
        //如果当前是老师实例登录则添加老师的fragment
        else {
            mFragmentList.add(new TeacherSignFragment());
            mFragmentList.add(new TeacherCheckSignFragment());
        }
        mFragmentPagerAdapter = new BaseFragmentPagerAdapter(MainActivity.this, fragmentManager, mFragmentList);
        activityMainViewpagerVp.setAdapter(mFragmentPagerAdapter);
        appBarMainTl.setupWithViewPager(activityMainViewpagerVp);
    }


    /**
     * 应用启动的一些配置属性
     */
    public void initConfig() {
    }

    public void showClassFragment() {


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this, "返回了", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
