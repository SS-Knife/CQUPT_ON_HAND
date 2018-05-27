package com.example.cqupt_on_hand.Body.Body_Navigation.Timetable_Mess;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cqupt_on_hand.Mess.Calendar;
import com.example.cqupt_on_hand.Mess.Decoration;
import com.example.cqupt_on_hand.Mess.HttpConnectionUtil;
import com.example.cqupt_on_hand.Mess.JsonAnalyze;
import com.example.cqupt_on_hand.R;
import com.example.cqupt_on_hand.Register.Register;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import static android.support.constraint.Constraints.TAG;
import static com.example.cqupt_on_hand.Register.Register.StuNum;


/**
 * Created by 郝书逸 on 2018/5/26.
 */

public class WeekFragment extends Fragment{
    static Integer[][][] id;
    ArrayList<Class_detail> class_details=new ArrayList<Class_detail>();
    private View view;
    private static int[] date={5,3};
    Calendar calendar=new Calendar();
    static JsonAnalyze jsonAnalyze =JsonAnalyze.newInstance();
    private  String[]titles = {"第一周","第二周","第三周","第四周","第五周","第六周","第七周","第八周","第九周","第十周","第十一周","第十二周","第十三周","第十四周","第十五周","第十六周","第十七周","第十八周","第十九周","第二十周","第二十一周","第二十二周","第二十三周","第二十四周","第二十五周"};
    private String title;
    private int week = 0;
    private static final String key = "EXTRA";
    RecyclerView recyclerView;
    static Map<String, String> map = new HashMap<String, String>();


    public static WeekFragment newInstance(String title){
        map.put("stu_num", StuNum);
        map.put("forceFetch","false");
        jsonAnalyze.parseCourseListJson(HttpConnectionUtil.getHttp().postRequset("https://wx.idsbllp.cn/api/kebiao\n",map));
        Bundle bundle = new Bundle();
        bundle.putString(key,title);
        WeekFragment fragment = new WeekFragment();
        fragment.setArguments(bundle);
        id = jsonAnalyze.getId();
        return fragment;
    }

    public void initdate1(){
        Integer id_this;
        String classroom;
        String name;
        String time;
        String[]num={"一二节","三四节","五六节","七八节","九十节","十一十二节"};
        // TODO: 2018/5/26 获取class_details
        for (int i=0;i<6;i++){
            for(int j=0;j<7;j++){
                Class_detail class_detail=new Class_detail();
                id_this=id[week][j][i];
                if (id_this!=null){
                    classroom=jsonAnalyze.getCourses().get(id_this).getClassroom();
                    name=jsonAnalyze.getCourses().get(id_this).getCourse();
                    time=jsonAnalyze.getCourses().get(id_this).getLesson();
                    class_detail.setClassroom(classroom);
                    class_detail.setName(name);
                    for (int k = 0; k < 6; k++) {
                        if (time.equals(num[k])){
                            class_detail.setNumber(k);
                        }
                    }
                }
                class_details.add(class_detail);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            title = bundle.getString(key);
            for (int i=0;i<25;i++){
                if(titles[i].equals(title)){
                    week = i;
                }
            }
        }

        if (view == null) {
            view = inflater.inflate(R.layout.weekfragment, container, false);
        }
        initdate2();
        initdate1();
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        //此项用于更改recyclerview的内部布局方式；
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(7,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        RecyclerAdapter recyclerAdapter=new RecyclerAdapter(class_details,getContext());
        recyclerView.addItemDecoration(new Decoration(7,0,true));
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);
        return view;
    }
    public void initdate2(){
        TextView monday=view.findViewById(R.id.mon_date);
        TextView tuseday=view.findViewById(R.id.tus_date);
        TextView wednesday=view.findViewById(R.id.wedn_date);
        TextView thursday=view.findViewById(R.id.thur_date);
        TextView friday=view.findViewById(R.id.fri_date);
        TextView saturday=view.findViewById(R.id.sat_date);
        TextView sunday=view.findViewById(R.id.sun_date);
        TextView month=view.findViewById(R.id.month);
        monday.setText(date[0]+"日");
        date=calendar.addDate(date);
        tuseday.setText(date[0]+"日");
        date=calendar.addDate(date);
        wednesday.setText(date[0]+"日");
        date=calendar.addDate(date);
        thursday.setText(date[0]+"日");
        date=calendar.addDate(date);
        friday.setText(date[0]+"日");
        date=calendar.addDate(date);
        saturday.setText(date[0]+"日");
        date=calendar.addDate(date);
        sunday.setText(date[0]+"日");
        date=calendar.addDate(date);
        month.setText(date[1]+"月");
    }
}
