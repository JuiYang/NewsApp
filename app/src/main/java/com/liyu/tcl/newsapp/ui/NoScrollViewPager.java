package com.liyu.tcl.newsapp.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 *  自定义ViewPager屏蔽滑动功能
 * */
public class NoScrollViewPager extends ViewPager {

    public NoScrollViewPager(Context context){
        super(context);
    }

    // 在布局文件中使用该类的时候，实例化该类调用该类的构造方法，这个方法不能少，不然程序会崩
    public NoScrollViewPager(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }


    // 重写触摸事件，并将其消费掉，不让其在具有滑动功能
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
}
