package com.example.cqupt_on_hand.Body.Body_Navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cqupt_on_hand.Mess.HttpConnectionUtil;
import com.example.cqupt_on_hand.Mess.ImageStorage.PicLoader;
import com.example.cqupt_on_hand.Mess.JsonAnalyze;
import com.example.cqupt_on_hand.R;

import java.util.HashMap;
import java.util.Map;

import static com.example.cqupt_on_hand.Mess.JsonAnalyze.studentInformation;
import static com.example.cqupt_on_hand.Register.Register.IdCode;
import static com.example.cqupt_on_hand.Register.Register.StuNum;

/**
 * Created by 郝书逸 on 2018/5/25.
 */

public class PersonalFragment  extends Fragment {
    static JsonAnalyze jsonAnalyze;
    ImageView avatar;
    TextView name;
    TextView saying;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.personalfragment, container, false);
        Map<String, String> map = new HashMap<String, String>();
        map.put("stuNum",StuNum);
        map.put("idNum",IdCode);
        jsonAnalyze.parseStudentJson(HttpConnectionUtil.getHttp().postRequset("https://wx.idsbllp.cn/cyxbsMobile/index.php/Home/Person/search",map));
        avatar = view.findViewById(R.id.avatar);
        name = view.findViewById(R.id.name);
        saying = view.findViewById(R.id.saying);
        PicLoader picLoader= new PicLoader(getContext());
        picLoader.show(avatar,studentInformation.getPhoto_thumbnail_src());
        name.setText(studentInformation.getNickname());
        saying.setText(studentInformation.getIntroduction());
        return view;
    }


}
