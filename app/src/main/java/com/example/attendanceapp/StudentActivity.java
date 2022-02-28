package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private String Class_name,Course_name;
    private int position;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<StudentItem> studentItems=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        Intent intent=getIntent();
        Class_name= intent.getStringExtra("classname");
        Course_name=intent.getStringExtra("coursename");
        position=intent.getIntExtra("position",-1);

        setToolbar();

        recyclerView=findViewById(R.id.st_recyclerview_id);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        studentAdapter=new StudentAdapter(this,studentItems);
        recyclerView.setAdapter(studentAdapter);
        
        studentAdapter.setOnItemClicklistener(position -> changeStatus(position));




    }

    private void changeStatus(int position) {

        String status=studentItems.get(position).getStatus();
        if(status.equals("P"))
        {
            status="A";
        }
        else
        {
            status="P";
        }
        studentItems.get(position).setStatus(status);
        studentAdapter.notifyItemChanged(position);
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

        toolbar.inflateMenu(R.menu.student_menu);
        toolbar.setOnMenuItemClickListener(menuItem -> onMenuItemClick(menuItem));
    }

    private boolean onMenuItemClick(MenuItem menuItem) {
       if(menuItem.getItemId()==R.id.add_student)
        {
          showAddStudentDialog();

        }
        return true;
    }

    private void showAddStudentDialog() {

        Mydialog dialog=new Mydialog();
        dialog.show(getSupportFragmentManager(),Mydialog.STUDENT_ADD_DIALOG);
        dialog.setListener((roll,name)->addStudent(roll,name));
    }

    private void addStudent(String roll, String name) {

        studentItems.add(new StudentItem(roll,name));
        studentAdapter.notifyDataSetChanged();
    }
}