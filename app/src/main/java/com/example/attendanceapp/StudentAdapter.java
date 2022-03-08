package com.example.attendanceapp;

import android.content.Context;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
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



    public static class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        TextView student_roll;
      TextView student_name;
      TextView student_status;
      CardView cardView;


        public StudentViewHolder(@NonNull View itemView, OnItemClicklistener onItemClicklistener ) {
            super(itemView);

            student_roll=itemView.findViewById(R.id.student_roll_id);
            student_name=itemView.findViewById(R.id.student_name_id);
            student_status=itemView.findViewById(R.id.student_status_id);
            cardView=itemView.findViewById(R.id.card_view_id);
            itemView.setOnClickListener(v->onItemClicklistener.OnClick(getAdapterPosition()));
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(getAdapterPosition(),0,0,"Edit");
            menu.add(getAdapterPosition(),1,0,"Delete");
        }
    }



    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.studentitem,parent,false);

        return new StudentViewHolder(itemview,onItemClicklistener);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {

    holder.student_roll.setText(studentItems.get(position).getRoll()+"");
    holder.student_name.setText(studentItems.get(position).getName());
    holder.student_status.setText(studentItems.get(position).getStatus());
    holder.cardView.setCardBackgroundColor(getColor(position));
    }

    private int getColor(int position) {
        String status=studentItems.get(position).getStatus();
        if(status.equals("P"))
        {
return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.present)));
        }
        else if(status.equals("A"))
        {
            return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.absent)));
        }
        return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.normal)));
    }

    @Override
    public int getItemCount() {
        return studentItems.size();
    }



}
