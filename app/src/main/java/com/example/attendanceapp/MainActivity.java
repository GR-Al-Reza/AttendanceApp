package com.example.attendanceapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingbutton;
    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar;


    ArrayList<ClassItem> classItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingbutton = findViewById(R.id.floating_button);
        floatingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog();

            }
        });
        recyclerView = findViewById(R.id.recyclerview_id);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        customAdapter = new CustomAdapter(this, classItems);
        recyclerView.setAdapter(customAdapter);

        customAdapter.setOnItemClicklistener(position -> getItemActivity(position));


        setToolbar();

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
        dialog.setListener((class_name, subject_name) -> addclass(class_name, subject_name));


    }

    private void addclass(String class_name, String subject_name) {

        classItems.add(new ClassItem(class_name, subject_name));

        customAdapter.notifyDataSetChanged();
    }
}