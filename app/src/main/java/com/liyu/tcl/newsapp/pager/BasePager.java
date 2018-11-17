package com.liyu.tcl.newsapp.pager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.liyu.tcl.newsapp.R;

public class BasePager {

    public Context context;
    public View rootView;

    public ImageButton imageButton;
    public TextView title_text;
    public FrameLayout base_fragment;


    public BasePager(Context context){
        this.context = context;
        rootView = initView();
    }

    public View initView(){
        // 基类页面
        View view = View.inflate(context,R.layout.base_pager,null);
        imageButton = (ImageButton) view.findViewById(R.id.title_img);
        title_text = (TextView) view.findViewById(R.id.title_text);
        base_fragment = (FrameLayout) view.findViewById(R.id.base_fragment);
        return view;
    }

    public void initData(){
    }
}
