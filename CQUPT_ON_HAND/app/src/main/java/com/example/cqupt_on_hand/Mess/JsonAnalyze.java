package com.example.cqupt_on_hand.Mess;

import android.os.Bundle;
import android.util.Log;

import com.example.cqupt_on_hand.Body.Body_Navigation.Timetable_Mess.Course;
import com.example.cqupt_on_hand.Body.Body_Navigation.Timetable_Mess.WeekFragment;
import com.example.cqupt_on_hand.Register.StudentInformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

/**
 * Created by 郝书逸 on 2018/4/13.
 */

public class JsonAnalyze {
    public static JsonAnalyze newInstance(){
        JsonAnalyze jsonAnalyze=new JsonAnalyze();
        return jsonAnalyze;
    }
    static Integer[][][] id ;
    static ArrayList<Course>courses;

    public static void parseCourseListJson(String text) {
        courses = new ArrayList<Course>();
        id = new Integer[25][7][6];
        try {
            JSONObject jsonObject = new JSONObject(text);

            Course course  = new Course();
            course.setTerm(jsonObject.getString("term"));
            course.setStuNum(jsonObject.getString("stuNum"));
            course.setCachedTimestamp(jsonObject.getString("cachedTimestamp"));
            course.setOutOfDateTimestamp(jsonObject.getString("outOfDateTimestamp"));
            course.setNowWeek(jsonObject.getString("nowWeek"));
            JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
            courses.add(course);

            for (int i = 0; i < jsonArray.length(); i++) {
                course  = new Course();
                JSONObject object = jsonArray.getJSONObject(i);
                course.setHash_day(object.getString("hash_day"));
                course.setHash_lesson(object.getString("hash_lesson"));
                course.setBegin_lesson(object.getString("begin_lesson"));
                course.setDay(object.getString("day"));
                course.setLesson(object.getString("lesson"));
                course.setCourse(object.getString("course"));
                course.setTeacher(object.getString("teacher"));
                course.setClassroom(object.getString("classroom"));
                course.setRawWeek(object.getString("rawWeek"));
                course.setWeekModel(object.getString("weekModel"));
                course.setWeekBegin(object.getString("weekBegin"));
                course.setWeekEnd(object.getString("weekEnd"));
                course.setType(object.getString("type"));
                course.setPeriod(object.getString("period"));
                JSONArray weekArray = object.getJSONArray("week");
                String[]week=new String[weekArray.length()];
                for (int j = 0; j < weekArray.length(); j++) {
                    week[j]=weekArray.get(j).toString();
                }
                course.setWeek(week);

                courses.add(course);
            }
            int day;
            int lesson;
            for (int i = 1; i < courses.size(); i++) {
                day = Integer.valueOf(courses.get(i).getHash_day()).intValue();
                lesson = Integer.valueOf(courses.get(i).getHash_lesson()).intValue();
                for (int j = 0; j < courses.get(i).getWeek().length; j++) {
                    id[Integer.valueOf(courses.get(i).getWeek()[j]).intValue()-1][day][lesson] = i;
                    Log.d(TAG, Integer.valueOf(courses.get(i).getWeek()[j]).intValue() -1+":"+day+":"+lesson+"="+i);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static Integer[][][] getId() {
        return id;
    }

    public static ArrayList<Course> getCourses() {
        return courses;
    }

    public static StudentInformation studentInformation=new StudentInformation();;
    public static void parseRegisterJson(String text) {
        try {
            JSONObject jsonObject = new JSONObject(text);
            studentInformation.setStatus(jsonObject.getInt("status"));
            JSONObject data=jsonObject.getJSONObject("data");
            studentInformation.setStuNum("stuNum");
            studentInformation.setName("name");
            studentInformation.setClassNum("classNum");
            studentInformation.setGender("gender");
            studentInformation.setCollege("college");
            studentInformation.setGrade("grade");
            studentInformation.setIdNum("idNum");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void parseStudentJson(String text) {
        try {
            JSONObject jsonObject = new JSONObject(text);
            JSONObject data=jsonObject.getJSONObject("data");
            studentInformation.setId("id");
            studentInformation.setIntroduction("introduction");
            studentInformation.setNickname("nickname");
            studentInformation.setPhoto_thumbnail_src("photo_thumbnail_src");
            studentInformation.setPhoto_src("photo_src");
            studentInformation.setUpdated_time("updated_time");
            studentInformation.setPhone("phone");
            studentInformation.setQq("qq");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}






