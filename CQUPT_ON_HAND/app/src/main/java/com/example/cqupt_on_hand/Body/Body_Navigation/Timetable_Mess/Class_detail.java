package com.example.cqupt_on_hand.Body.Body_Navigation.Timetable_Mess;

/**
 * Created by 郝书逸 on 2018/5/26.
 */

public class Class_detail {
    private String name;//课程名
    private String teacher;//任课教师
    private String classroom;//教室
    private int date;//当天日期
    private int weekday;//当天星期
    private int week;//所在星期数
    private int number;//当天第几节课
    private int maintime;//该课程总上课周数：1-16周
    private boolean type;//true必修 false选修
    private boolean exist;//是否存在

    public boolean isExist() {
        return exist;
    }

    public String getName() {
        return name;
    }

    public int getDate() {
        return date;
    }

    public String getClassroom() {
        return classroom;
    }

    public int getMaintime() {
        return maintime;
    }

    public int getNumber() {
        return number;
    }

    public int getWeek() {
        return week;
    }

    public int getWeekday() {
        return weekday;
    }

    public String getTeacher() {
        return teacher;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setMaintime(int maintime) {
        this.maintime = maintime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }
}
