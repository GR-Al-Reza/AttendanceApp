package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class StudentActivity extends AppCompatActivity {
    Toolbar toolbar;
    String Class_name,Course_name;
            int positon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        Intent intent=getIntent();
        Class_name= intent.getStringExtra("classname");
        Course_name=intent.getStringExtra("coursename");
        positon=intent.getIntExtra("position",-1);

        setToolbar();
    }
    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar_id);
        TextView title = toolbar.findViewById(R.id.title_toolbar_id);
        TextView subtitle = toolbar.findViewById(R.id.subtitle_toolbar_id);
        ImageButton backbutton = toolbar.findViewById(R.id.icon_back_id);
        ImageButton savebutton = toolbar.findViewById(R.id.save_button_id);

        title.setText(Class_name);
        subtitle.setText(Course_name);
        backbutton.setVisibility(View.VISIBLE);
        savebutton.setVisibility(View.VISIBLE);

        backbutton.setOnClickListener(v -> onBackPressed());
    }
}