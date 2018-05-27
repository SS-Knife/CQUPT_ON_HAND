package com.example.cqupt_on_hand.Body.Body_Navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cqupt_on_hand.R;

/**
 * Created by 郝书逸 on 2018/5/25.
 */

public class YouAskFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.youaskfragment, container, false);
        return view;
    }
}
