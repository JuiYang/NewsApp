package com.liyu.tcl.newsapp.fragment;


import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.liyu.tcl.newsapp.R;
import com.liyu.tcl.newsapp.activity.MainActivity;
import com.liyu.tcl.newsapp.pager.BasePager;
import com.liyu.tcl.newsapp.pager.GovaffairPager;
import com.liyu.tcl.newsapp.pager.HomePager;
import com.liyu.tcl.newsapp.pager.NewsCenterPager;
import com.liyu.tcl.newsapp.pager.SettingPager;
import com.liyu.tcl.newsapp.pager.SmartservicePager;
import com.liyu.tcl.newsapp.ui.NoScrollViewPager;
import com.liyu.tcl.newsapp.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;


public class ContentFragment extends BaseFragment {
    private static String TAG = ContentFragment.class.getSimpleName();
    private TextView textView;
    private RadioGroup radioGroup;


    private NoScrollViewPager  viewPager;

    public List<BasePager> pagerList;

    @Override
    public View initView() {
        LogUtil.i(TAG,"创建TextView对象");
//        textView = new TextView(context);
//        textView.setTextSize(20);
////        textView.setGravity(Gravity.CENTER_HORIZONTAL);
//        return textView;

        View view = View.inflate(context, R.layout.content_fragment,null);
        viewPager = (NoScrollViewPager ) view.findViewById(R.id.content_pagers);
        radioGroup = (RadioGroup) view.findViewById(R.id.rg_button);
        return view;
    }

    @Override
    public void initData() {
//        super.initData();
        LogUtil.i(TAG,"初始化TextView数据");
        // 创建子页面
        pagerList = new ArrayList<>();
        pagerList.add(new HomePager(context));
        pagerList.add(new NewsCenterPager(context));
        pagerList.add(new SmartservicePager(context));
        pagerList.add(new GovaffairPager(context));
        pagerList.add(new SettingPager(context));
        // 设置ViewPager适配器
        viewPager.setAdapter(new MyContentPagerAdapter());

        // 设置RadioGroup的选中状态改变监听器
        radioGroup.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        // 当某个页面被选中时初始化数据
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        // 默认选择home页面
        radioGroup.check(R.id.bt_home);
        // 默认初始化home页面数据
        pagerList.get(0).initData();
        // 默认是无法滑动左侧菜单
        isEnableSlidingMenuSlide(SlidingMenu.TOUCHMODE_NONE);
    }

    // 当选中某个页面时，才进行数据的加载，ViewPager仍会加载下个页面，但是不去联网请求数据
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }

        // 当某个页面被选中时加载数据
        @Override
        public void onPageSelected(int position) {
            pagerList.get(position).initData();
        }

        @Override
        public void onPageScrollStateChanged(int i) {
        }
    }

    public class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.bt_home:
                    // 设置不能滑动左菜单
                    isEnableSlidingMenuSlide(SlidingMenu.TOUCHMODE_NONE);
                    // false: 设置无动画
                    viewPager.setCurrentItem(0, false);
                    break;
                case R.id.bt_newscenter:
                    // NewsCenter设置为可以滑动左侧菜单
                    isEnableSlidingMenuSlide(SlidingMenu.TOUCHMODE_FULLSCREEN);
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.bt_smartserv:
                    isEnableSlidingMenuSlide(SlidingMenu.TOUCHMODE_NONE);
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.bt_gov:
                    isEnableSlidingMenuSlide(SlidingMenu.TOUCHMODE_NONE);
                    viewPager.setCurrentItem(3);
                    break;
                case R.id.bt_setting:
                    isEnableSlidingMenuSlide(SlidingMenu.TOUCHMODE_NONE);
                    viewPager.setCurrentItem(4);
                    break;
                default:
                    break;
            }
        }
    }

    private void isEnableSlidingMenuSlide(int slideWay){
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.getSlidingMenu().setTouchmodeMarginThreshold(slideWay);
    }


    public class MyContentPagerAdapter extends PagerAdapter{
        private List<BasePager> pagers;
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            // 获取各个页面实例
            BasePager basePager = pagerList.get(position);
//            // 页面预加载
//            basePager.initData();
            // 获取各个页面
            View view = basePager.rootView;
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return pagers.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }
    }
}
