package com.example.cqupt_on_hand.Body.Body_Navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cqupt_on_hand.Body.Body_Navigation.Explor_Mess.myviewpagerAdapter;
import com.example.cqupt_on_hand.Mess.ImageStorage.PicLoader;
import com.example.cqupt_on_hand.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郝书逸 on 2018/5/25.
 */

public class ExplorFragment extends Fragment {
    ViewPager viewPager;
    myviewpagerAdapter adapter;
    ArrayList<ImageView> list=new ArrayList<ImageView>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.explorfragment, container, false);
        viewPager=view.findViewById(R.id.viewpager);
        adapter=new myviewpagerAdapter(list);
        viewPager.setAdapter(adapter);
        return view;
    }


}
