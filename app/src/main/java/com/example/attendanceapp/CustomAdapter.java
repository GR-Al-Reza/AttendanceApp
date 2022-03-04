package com.example.attendanceapp;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ClassViewHolder> {
     Context context;
    ArrayList<ClassItem> classItems;

    private OnItemClicklistener onItemClicklistener;
    public interface OnItemClicklistener{

        void OnClick(int position);

    }

    public void setOnItemClicklistener(OnItemClicklistener onItemClicklistener) {
        this.onItemClicklistener = onItemClicklistener;
    }

    public CustomAdapter(Context context, ArrayList<ClassItem> classItems) {
        this.classItems = classItems;
        this.context=context;
    }



    public static class ClassViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        TextView class_name;
      TextView subject_name;


        public ClassViewHolder(@NonNull View itemView, OnItemClicklistener onItemClicklistener ) {
            super(itemView);

            class_name=itemView.findViewById(R.id.class_tv_id);
            subject_name=itemView.findViewById(R.id.subject_tv_id);
            itemView.setOnClickListener(v->onItemClicklistener.OnClick(getAdapterPosition()));
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(getAdapterPosition(),0,0,"Edit");
            menu.add(getAdapterPosition(),1,0,"Delete");

        }
    }


    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.classitem,parent,false);

        return new ClassViewHolder(itemview,onItemClicklistener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {

    holder.class_name.setText(classItems.get(position).getClass_name());
    holder.subject_name.setText(classItems.get(position).getClass_course());

    }

    @Override
    public int getItemCount() {
        return classItems.size();
    }



}
