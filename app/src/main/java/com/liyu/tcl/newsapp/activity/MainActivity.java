package com.liyu.tcl.newsapp.activity;

import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.liyu.tcl.newsapp.R;
import com.liyu.tcl.newsapp.utils.DensityUtil;

public class MainActivity extends SlidingFragmentActivity {
    private SlidingMenu slidingMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setBehindContentView(R.layout.left_menu);
        slidingMenu = getSlidingMenu();
        slidingMenu.setSecondaryMenu(R.layout.right_menu);
        // 设置显示宽度
        slidingMenu.setBehindOffset(DensityUtil.dip2px(MainActivity.this, 200));

        // 设置菜单的划出方向
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        // 设置菜单滑动方式
        slidingMenu.setTouchModeAbove(SlidingMenu.SLIDING_CONTENT);
        //绑定activity,设置菜单划出来的高度：全屏，actionBar下面
//        slidingMenu.attachToActivity(MainActivity.this, SlidingMenu.SLIDING_CONTENT);
    }
}
