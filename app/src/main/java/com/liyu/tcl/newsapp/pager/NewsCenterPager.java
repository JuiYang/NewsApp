package com.liyu.tcl.newsapp.pager;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class NewsCenterPager extends BasePager {

    public NewsCenterPager(Context context){
        super(context);
    }

    @Override
    public View initView() {
        return super.initView();
    }

    @Override
    public void initData() {
        super.initData();
        title_text.setText("新闻页面");
        TextView content_text = new TextView(context);
        content_text.setText("我是新闻页面内容栏");
        content_text.setGravity(Gravity.CENTER);
        base_fragment.addView(content_text);
    }
}
