package com.liyu.tcl.newsapp.pager;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class HomePager extends BasePager {

    public HomePager(Context context){
        super(context);
    }

    @Override
    public View initView() {
        return super.initView();
    }

    @Override
    public void initData() {
        super.initData();
        title_text.setText("主页面");
        TextView content_text = new TextView(context);
        content_text.setText("我是主页面内容栏");
        content_text.setGravity(Gravity.CENTER);
        base_fragment.addView(content_text);
    }
}
