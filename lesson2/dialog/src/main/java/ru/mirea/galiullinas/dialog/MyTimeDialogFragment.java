package ru.mirea.galiullinas.dialog;

import android.app.TimePickerDialog;
import android.content.Context;

import androidx.annotation.NonNull;

public class MyTimeDialogFragment extends TimePickerDialog {


    public MyTimeDialogFragment(Context context, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, listener, hourOfDay, minute, is24HourView);
    }
}
