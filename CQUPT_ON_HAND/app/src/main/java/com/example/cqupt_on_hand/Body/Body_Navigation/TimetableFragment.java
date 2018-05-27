package com.example.cqupt_on_hand.Body.Body_Navigation;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cqupt_on_hand.Body.Body_Navigation.Timetable_Mess.WeekFragment;
import com.example.cqupt_on_hand.Body.Body_Navigation.Timetable_Mess.WeekpagerAdapter;
import com.example.cqupt_on_hand.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by 郝书逸 on 2018/5/25.
 */

public class TimetableFragment extends Fragment {
    private ViewPager viewpager;
    private TabLayout tablayout;
    private List<Fragment> fragments;
    private  String[]titles = {"第一周","第二周","第三周","第四周","第五周","第六周","第七周","第八周","第九周","第十周","第十一周","第十二周","第十三周","第十四周","第十五周","第十六周","第十七周","第十八周","第十九周","第二十周","第二十一周","第二十二周","第二十三周","第二十四周","第二十五周"};
    private WeekpagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.timetablefragment, container, false);
        this.tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        this.viewpager = (ViewPager) view.findViewById(R.id.weekviewpager);
        initData();
        return view;
    }
    private void initData() {

        fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            fragments.add(WeekFragment.newInstance(titles[i]));
        }
        adapter = new WeekpagerAdapter(getChildFragmentManager(),fragments);
        adapter.setTitles(titles);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);

    }

}
