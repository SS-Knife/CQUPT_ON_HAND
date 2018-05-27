package com.example.cqupt_on_hand.Mess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 郝书逸 on 2018/5/27.
 */

public class Calendar {
    public int[] addDate(int[] date){
        int[] full={31,30,31,30,31,31,31,30,31,30,31};
        date[0]++;
        if (date[0]>full[date[1]-3]){
            date[0]=1;
            date[1]++;
        }
        return date;
    }
}


