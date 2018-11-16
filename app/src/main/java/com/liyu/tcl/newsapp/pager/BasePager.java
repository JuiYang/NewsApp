package com.liyu.tcl.newsapp.pager;

import android.content.Context;
import android.view.View;

import com.liyu.tcl.newsapp.R;

public class BasePager {

    private Context context;
    public View rootView;

    public BasePager(Context context){
        this.context = context;
        rootView = initView();
    }

    public View initView(){
        // 基类页面
        View view = View.inflate(context,R.layout.base_pager,null);
        return view;
    }

    public void initData(){

    }
}
