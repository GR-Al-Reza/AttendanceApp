package com.example.attendanceapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class MyCalender extends DialogFragment {
    Calendar calendar=Calendar.getInstance();
    private int year=calendar.YEAR;
    private int month=calendar.MONTH;
    private int day=calendar.DAY_OF_MONTH;

    private interface OncalendarOkclickListener{

    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(),((view, year, month, dayOfMonth) -> {

        }),year,month,day);
    }
}
