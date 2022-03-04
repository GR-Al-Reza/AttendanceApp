package com.example.attendanceapp;

public class ClassItem {
    private long c_id;

    public ClassItem(long c_id, String class_name, String class_course) {
        this.c_id = c_id;
        this.class_name = class_name;
        this.class_course = class_course;
    }

    public long getC_id() {
        return c_id;
    }

    public void setC_id(long c_id) {
        this.c_id = c_id;
    }

    private String class_name;
    private String class_course;

    public ClassItem(String class_name, String class_course) {
        this.class_name = class_name;
        this.class_course = class_course;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getClass_course() {
        return class_course;
    }

    public void setClass_course(String class_course) {
        this.class_course = class_course;
    }
}
