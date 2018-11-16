package com.liyu.tcl.newsapp.fragment;


import android.view.View;
import android.widget.TextView;

import com.liyu.tcl.newsapp.utils.LogUtil;

public class LeftFragment extends BaseFragment {
    private static String TAG = LeftFragment.class.getSimpleName();
    private TextView textView;

    @Override
    public View initView() {
        LogUtil.i(TAG,"创建TextView对象");
        textView = new TextView(context);
        textView.setTextSize(20);
//        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        return textView;
    }

    @Override
    public void initData() {
//        super.initData();
        LogUtil.i(TAG,"初始化TextView数据");
        textView.setText("正文Fragment页面");
    }
}
