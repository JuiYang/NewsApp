package com.liyu.tcl.newsapp.fragment;


import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.liyu.tcl.newsapp.R;
import com.liyu.tcl.newsapp.utils.LogUtil;


public class ContentFragment extends BaseFragment {
    private static String TAG = ContentFragment.class.getSimpleName();
    private TextView textView;
    private RadioGroup radioGroup;

    @Override
    public View initView() {
        LogUtil.i(TAG,"创建TextView对象");
//        textView = new TextView(context);
//        textView.setTextSize(20);
////        textView.setGravity(Gravity.CENTER_HORIZONTAL);
//        return textView;

        View view = View.inflate(context, R.layout.content_fragment,null);
        radioGroup = (RadioGroup) view.findViewById(R.id.rg_button);
        return view;
    }

    @Override
    public void initData() {
//        super.initData();
        LogUtil.i(TAG,"初始化TextView数据");
//        textView.setText("正文Fragment页面");
        radioGroup.check(R.id.bt_home);
    }
}
