package com.liyu.tcl.newsapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class CacheUtil {

    // 查看该用户之前是否使用过该应用
    public static boolean getBoolean(Context context, String key){
        // 创建本地文件数据库， 创建成功返回true
        SharedPreferences sharedPreferences = context.getSharedPreferences("news_app",MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    // 存储第一次使用记录
    public static void putBoolean(Context context, String key, Boolean value){
        SharedPreferences.Editor editor = context.getSharedPreferences("news_app", MODE_PRIVATE).edit();
        editor.putBoolean(key,value);
        // 提交数据； apply() == commit()
        editor.apply();
    }
}
