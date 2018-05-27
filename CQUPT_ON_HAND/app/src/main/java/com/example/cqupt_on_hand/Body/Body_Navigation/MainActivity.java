package com.example.cqupt_on_hand.Body.Body_Navigation;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.cqupt_on_hand.R;
import com.example.cqupt_on_hand.Register.Register;

import static com.example.cqupt_on_hand.Register.Register.lalala;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {
    public final static String ACTION_EXIT_SYSTEM = "sys_exit";
    private FragmentManager manager;
    private FragmentTransaction transaction;
    ExplorFragment explorFragment;
    PersonalFragment personalFragment;
    TimetableFragment timetableFragment;
    YouAskFragment youAskFragment;
    FrameLayout frameLayout;
    RadioButton tabTimetable;
    RadioButton tabYouAsk;
    RadioButton tabPersonal;
    RadioButton tabExplor;
    RadioGroup tabBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lalala.finish();
        tabBar=(RadioGroup)findViewById(R.id.tab_bar);
        RadioButton tabTimetable = (RadioButton) tabBar.getChildAt(0);
        tabTimetable.setChecked(true);
        tabBar.setOnCheckedChangeListener(this);
        initFragment();
    }
    private void initFragment() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        timetableFragment = new TimetableFragment();
        transaction.add(R.id.frame_layout,timetableFragment);
        transaction.commit();
    }



    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkID) {
        switch (checkID) {
            case R.id.tab_Timetable:
                FragmentTransaction ft1 = manager.beginTransaction();
                hideAll(ft1);
                if (timetableFragment!=null){
                    ft1.show(timetableFragment);
                }else {
                    timetableFragment=new TimetableFragment();
                    ft1.add(R.id.frame_layout,timetableFragment);
                }
                ft1.commit();
                break;
            case R.id.tab_Explor:
                FragmentTransaction ft2 = manager.beginTransaction();
                hideAll(ft2);
                if (explorFragment!=null){
                    ft2.show(explorFragment);
                }else {
                    explorFragment = new ExplorFragment();
                    ft2.add(R.id.frame_layout,explorFragment);
                }
                ft2.commit();
                break;
            case R.id.tab_Personal:
                FragmentTransaction ft3 = manager.beginTransaction();
                hideAll(ft3);
                if (personalFragment!=null){
                    ft3.show(personalFragment);
                }else {
                    personalFragment = new PersonalFragment();
                    ft3.add(R.id.frame_layout, personalFragment);
                }
                ft3.commit();
                break;
            case R.id.tab_YouAsk:
                FragmentTransaction ft4 = manager.beginTransaction();
                hideAll(ft4);
                if (youAskFragment!=null){
                    ft4.show(youAskFragment);
                }else {
                    youAskFragment = new YouAskFragment();
                    ft4.add(R.id.frame_layout, youAskFragment);
                }
                ft4.commit();
                break;
        }

    }
    private void hideAll(FragmentTransaction ft){
        if (ft==null){
            return;
        }
        if (timetableFragment!=null){
            ft.hide(timetableFragment);
        }
        if (explorFragment!=null){
            ft.hide(explorFragment);
        }
        if (youAskFragment!=null){
            ft.hide(youAskFragment);
        }
        if (personalFragment!=null){
            ft.hide(personalFragment);
        }
    }

}
