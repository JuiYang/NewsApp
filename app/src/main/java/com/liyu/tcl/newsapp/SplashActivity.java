package com.liyu.tcl.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import com.liyu.tcl.newsapp.activity.GuideActivity;
import com.liyu.tcl.newsapp.activity.MainActivity;
import com.liyu.tcl.newsapp.utils.CacheUtil;
import com.liyu.tcl.newsapp.utils.LogUtil;

import org.xutils.x;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getSimpleName();
    private LinearLayout linearLayout;
    public static final String START_ACTIVITY = "first_start";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        x.Ext.init(getApplication());

        linearLayout = (LinearLayout) findViewById(R.id.linear_layout);

        // 渐变，旋转， 缩放
        // 旋转
        RotateAnimation rotate = new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF ,0.5f);
//        rotate.setDuration(2000);
        rotate.setFillAfter(true);

        // 渐变
        AlphaAnimation alphan = new AlphaAnimation(0,1);
//        alphan.setDuration(2000);
        alphan.setFillAfter(true);

        // 缩放
        ScaleAnimation scale = new ScaleAnimation(0,1, 0, 1,ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF,0.5f);
//        scale.setDuration(2000);
        scale.setFillAfter(true);

        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(rotate);
        animationSet.addAnimation(alphan);
        animationSet.addAnimation(scale);
        animationSet.setDuration(2000);

        linearLayout.setAnimation(animationSet);

        // 对动画进行监听
        animationSet.setAnimationListener(new MyAnimationListener());


    }

    class MyAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {
            LogUtil.i(TAG, "Animation start...");
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            LogUtil.i(TAG, "Animation end...");
            // 动画结束进入引导界面
            boolean flag = CacheUtil.getBoolean(SplashActivity.this, START_ACTIVITY);
            LogUtil.i(TAG,"flag: "+flag);
            if (flag){
                // 1. 用户非首次进入，则直接进入应用界面
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }else{
                // 2. 用户首次进入，则需要进入引导界面
                Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
                startActivity(intent);
            }
            // 关闭当前页面
            finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            LogUtil.i(TAG, "Animation repeat...");
        }
    }
}
