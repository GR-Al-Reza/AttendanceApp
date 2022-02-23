package com.example.attendanceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
     Context context;
    ArrayList<StudentItem> studentItems;

    private OnItemClicklistener onItemClicklistener;
    public interface OnItemClicklistener{

        void OnClick(int position);

    }

    public void setOnItemClicklistener(OnItemClicklistener onItemClicklistener) {
        this.onItemClicklistener = onItemClicklistener;
    }

    public StudentAdapter(Context context, ArrayList<StudentItem> studnetItems) {
        this.studentItems = studnetItems;
        this.context=context;
    }



    public static class StudentViewHolder extends RecyclerView.ViewHolder{

        TextView student_roll;
      TextView student_name;
      TextView student_status;


        public StudentViewHolder(@NonNull View itemView, OnItemClicklistener onItemClicklistener ) {
            super(itemView);

            student_roll=itemView.findViewById(R.id.student_roll_id);
            student_name=itemView.findViewById(R.id.student_name_id);
            student_status=itemView.findViewById(R.id.student_status_id);
            itemView.setOnClickListener(v->onItemClicklistener.OnClick(getAdapterPosition()));

        }
    }



    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.studentitem,parent,false);

        return new StudentViewHolder(itemview,onItemClicklistener);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {

    holder.student_roll.setText(studentItems.get(position).getRoll());
    holder.student_name.setText(studentItems.get(position).getName());
    holder.student_status.setText(studentItems.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return studentItems.size();
    }



}
