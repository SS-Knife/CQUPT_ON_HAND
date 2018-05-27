package com.example.cqupt_on_hand.Register;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cqupt_on_hand.Body.Body_Navigation.MainActivity;
import com.example.cqupt_on_hand.Mess.HttpConnectionUtil;
import com.example.cqupt_on_hand.Mess.JsonAnalyze;
import com.example.cqupt_on_hand.R;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static com.example.cqupt_on_hand.Mess.JsonAnalyze.studentInformation;

public class Register extends Activity {
    public static String StuNum;
    public static String IdCode;
    static JsonAnalyze jsonAnalyze;
    public static Register lalala;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        lalala = this;
        EditText account=findViewById(R.id.account);
        EditText password=findViewById(R.id.password);
        Button register=findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText account=findViewById(R.id.account);
                EditText password=findViewById(R.id.password);
                StuNum = account.getText().toString();
                IdCode = password.getText().toString();
                Map<String, String> map = new HashMap<String, String>();
                map.put("stuNum",StuNum);
                map.put("idNum",IdCode);
                jsonAnalyze.parseRegisterJson(HttpConnectionUtil.getHttp().postRequset("https://wx.idsbllp.cn/api/verify\n",map));
                if(studentInformation.getStatus()==200){
                    Intent intent =new Intent(Register.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
