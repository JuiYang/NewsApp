package com.liyu.tcl.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.liyu.tcl.newsapp.utils.CacheUtil;
import com.liyu.tcl.newsapp.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {
    private Button enterApp;
    private ViewPager viewPager;
    private LinearLayout pointLL;
    private List<ImageView> imageViews;
    private ImageView redOvalImgView;
    private int viewWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        enterApp = (Button) findViewById(R.id.btn_enter_app);
        viewPager = (ViewPager) findViewById(R.id.guide_viewpage);
        pointLL = (LinearLayout) findViewById(R.id.ll_point_group);
        redOvalImgView = (ImageView) findViewById(R.id.red_oval);

        // 添加图片
        int[] ids = new int[]{
                R.drawable.guide_1,
                R.drawable.guide_2,
                R.drawable.guide_3
        };


        imageViews = new ArrayList<>();
        for (int i=0;i<ids.length; i++){
            ImageView imageView = new ImageView(GuideActivity.this);
            // 设置背景颜色，background：图片将铺满整个页面， src: 图片只展示图片本身大小
            imageView.setBackgroundResource(ids[i]);
            imageViews.add(imageView);

            // 添加点
            ImageView imagePoint = new ImageView(GuideActivity.this);
            imagePoint.setBackgroundResource(R.drawable.point_gray_view);
            // 单位是像素点
            // 作适配： 将dip转成像素点
            int px = DensityUtil.dip2px(GuideActivity.this, 10);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(px,px);
            if (i != 0) {
                params.setMarginStart(px);
            }
            imagePoint.setLayoutParams(params);
            pointLL.addView(imagePoint);
        }

        viewPager.setAdapter(new MyPagesAdapter(imageViews));
        // 更具View生命周期，当时土执行到onLayout/onDraw的时候，视图的宽、高以及边距都可以获得
        redOvalImgView.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());

        // 获取屏幕活动百分比
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());

        enterApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 保存曾经进入到主页面参数
                CacheUtil.putBoolean(GuideActivity.this, SplashActivity.START_ACTIVITY, true);
                // 跳转到主页面
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                // 关闭引导页面
                finish();
            }
        });


    }

    // 监听滑动页面
    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{

        // 页面回调会回调该方法
        /**
         * int i: 当前滑动页面的位置
         * float v: 滑动百分比
         * int i1: 滑动的像素点
         * */
        @Override
        public void onPageScrolled(int position, float positionOffSet, int i1) {
            // 两点之间的滑动距离= 起始距离 + 滑动距离
            int leftMarge = (int) ( viewWidth * position + positionOffSet * viewWidth);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) redOvalImgView.getLayoutParams();
            params.leftMargin = leftMarge;
            redOvalImgView.setLayoutParams(params);
        }

        // 当页面被选中会回调该方法
        @Override
        public void onPageSelected(int i) {
            if (i == imageViews.size()-1){
                // 最后一个页面
                enterApp.setVisibility(View.VISIBLE);
            }else{
                // 其他页面
                enterApp.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }


    // 使用OnGlobalLayoutListener来获得一个视图的真实高度。
    class MyOnGlobalLayoutListener  implements ViewTreeObserver.OnGlobalLayoutListener{
        @Override
        public void onGlobalLayout() {
            // 获取两个点之间的间距
            viewWidth = pointLL.getChildAt(1).getLeft() - pointLL.getChildAt(0).getLeft();
            // 不止执行一次
            redOvalImgView.getViewTreeObserver().removeOnGlobalLayoutListener(MyOnGlobalLayoutListener.this);
        }
    }

    class MyPagesAdapter extends PagerAdapter {
        private List<ImageView> imageViewList;
        public MyPagesAdapter(List<ImageView> imageViews){
            this.imageViewList = imageViews;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public int getCount() {
            if (imageViewList != null && imageViewList.size()>0) {
                return imageViewList.size();
            }else{
                return 0;
            }
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(imageViewList.get(position));
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(imageViewList.get(position));
            return imageViewList.get(position);
        }
    }

}
