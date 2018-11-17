package com.liyu.tcl.newsapp.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.liyu.tcl.newsapp.R;
import com.liyu.tcl.newsapp.fragment.ContentFragment;
import com.liyu.tcl.newsapp.fragment.LeftFragment;
import com.liyu.tcl.newsapp.utils.DensityUtil;

public class MainActivity extends SlidingFragmentActivity {
    public static final String MAIN_CONTENT = "main_content";
    public static final String MAIN_LEFT_MENU = "main_left_menu";
    public SlidingMenu slidingMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setBehindContentView(R.layout.left_menu);
        slidingMenu = getSlidingMenu();
//        slidingMenu.setSecondaryMenu(R.layout.content_fragment);

        // 设置菜单的划出方向
        slidingMenu.setMode(SlidingMenu.LEFT);
        // 设置菜单滑动方式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //绑定activity,设置菜单划出来的高度：全屏，actionBar下面
//        slidingMenu.attachToActivity(MainActivity.this, SlidingMenu.SLIDING_CONTENT);

        // 设置显示宽度
        slidingMenu.setBehindOffset(DensityUtil.dip2px(MainActivity.this, 200));

        initFragment();
    }

    private void initFragment(){
        // 获取fragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // 操作
        // 替换主页
        fragmentTransaction.add(R.id.main_view, new ContentFragment(), MAIN_CONTENT);
        // 替换做菜单
        fragmentTransaction.add(R.id.main_left_menu, new LeftFragment(), MAIN_LEFT_MENU);
        // 提交事务
        fragmentTransaction.commit();
    }
}
