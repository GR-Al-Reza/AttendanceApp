package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingbutton;
    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar;


    Databhelper databhelper;



    ArrayList<ClassItem> classItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // dbHelper=new DbHelper(this);
        databhelper=new Databhelper(this);

        floatingbutton = findViewById(R.id.floating_button);
        floatingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog();

            }
        });
       loadData();

        recyclerView = findViewById(R.id.recyclerview_id);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        customAdapter = new CustomAdapter(this, classItems);
        recyclerView.setAdapter(customAdapter);

        customAdapter.setOnItemClicklistener(position -> getItemActivity(position));


        setToolbar();

    }

    //database work load data
    private void loadData()
    {

        Cursor cursor=databhelper.getClassTable();
        classItems.clear();
        while(cursor.moveToNext())
        {
            int id=cursor.getInt(cursor.getColumnIndex(databhelper.C_ID));
            String class_name=cursor.getString(cursor.getColumnIndex(databhelper.CLASS_NAME_KEY));
            String subject_name=cursor.getString(cursor.getColumnIndex(databhelper.SUBJECT_NAME_KEY));
            classItems.add(new ClassItem(id,class_name,subject_name));
        }
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar_id);
        TextView title = toolbar.findViewById(R.id.title_toolbar_id);
        TextView subtitle = toolbar.findViewById(R.id.subtitle_toolbar_id);
        ImageButton backbutton = toolbar.findViewById(R.id.icon_back_id);
        ImageButton savebutton = toolbar.findViewById(R.id.save_button_id);

        title.setText("Attendence App");
        subtitle.setVisibility(View.GONE);
        backbutton.setVisibility(View.INVISIBLE);
        savebutton.setVisibility(View.INVISIBLE);
    }

    private void getItemActivity(int position) {

        Intent intent = new Intent(this, StudentActivity.class);

        intent.putExtra("classname", classItems.get(position).getClass_name());
        intent.putExtra("coursename", classItems.get(position).getClass_course());
        intent.putExtra("position", position);
        startActivity(intent);
    }

    private void showdialog() {

        Mydialog dialog = new Mydialog();
        dialog.show(getSupportFragmentManager(), Mydialog.CLASS_ADD_DIALOG);
        dialog.setListener((class_name, subject_name) -> addClass(class_name, subject_name));


    }

    private void addClass(String class_name, String subject_name) {
       long cid= databhelper.addClass(class_name,subject_name);
        ClassItem classItem=new ClassItem(cid,class_name,subject_name);

        classItems.add(classItem);
        //classItems.add(new ClassItem(class_name,subject_name));

        customAdapter.notifyDataSetChanged();


    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case 0:
                showUpdateDialog(item.getGroupId());
                break;
            case 1:
                deleteClass(item.getGroupId());
        }
        return super.onContextItemSelected(item);
    }

    private void showUpdateDialog(int position) {
        Mydialog dialog=new Mydialog();
       dialog.show(getSupportFragmentManager(),Mydialog.CLASS_UPDATE_DIALOG);
       dialog.setListener((classname,subjectname)->updatcClass(position,classname,subjectname));

    }

    private void updatcClass(int position, String classname, String subjectname) {
        databhelper.updateClass(classItems.get(position).getC_id(),classname,subjectname);
        classItems.get(position).setClass_name(classname);
        classItems.get(position).setClass_course(subjectname);
        customAdapter.notifyItemChanged(position);

    }

    private void deleteClass(int position) {

        databhelper.deleteClass(classItems.get(position).getC_id());
        classItems.remove(position);
        customAdapter.notifyItemRemoved(position);
    }

}