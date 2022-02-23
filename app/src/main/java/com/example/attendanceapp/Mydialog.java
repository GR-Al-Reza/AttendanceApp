package com.example.attendanceapp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class Mydialog extends DialogFragment {

    public static final String CLASS_ADD_DIALOG="addclass";
    private OnClickLintener listener;
    public interface OnClickLintener
    {
        void onClick(String text1,String text2);
    }
    public void setListener(OnClickLintener listener)
    {
        this.listener=listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog=null;
        if(getTag().equals(CLASS_ADD_DIALOG)) dialog=getAddClassDialog();

        return dialog;
    }

    private Dialog getAddClassDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.dialog_layout,null);
        builder.setView(view);

        TextView title=view.findViewById(R.id.title_dialog_id);
        title.setText("Add new class");

        EditText class_edt=view.findViewById(R.id.edt01_id);
        EditText sub_edt=view.findViewById(R.id.edt02_id);
        class_edt.setHint("Class name");
        sub_edt.setHint("Course name");

        Button cancel=view.findViewById(R.id.cancle_btn);
        Button add=view.findViewById(R.id.add_btn);

        cancel.setOnClickListener(v -> dismiss());

        add.setOnClickListener(v -> {

            String class_name=class_edt.getText().toString();
            String sub_name=sub_edt.getText().toString();

            listener.onClick(class_name,sub_name);

                  dismiss();
                }
        );

        return builder.create();
    }
}
