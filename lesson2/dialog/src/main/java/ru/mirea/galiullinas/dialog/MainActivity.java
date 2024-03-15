package ru.mirea.galiullinas.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.concurrent.TimeUnit;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickShowDialog(View view) {
        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
    }

    public void onOkClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \'Иду дальше\'",
                Toast.LENGTH_SHORT).show();
    }


    public void onCancelClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \'Нет\'",
                Toast.LENGTH_SHORT).show();
    }


    public void onNeutralClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \'На паузе\'",
                Toast.LENGTH_SHORT).show();
    }

    public void onClickShowTimeDialog(View view) {
        MyTimeDialogFragment timeDialogFragment = new MyTimeDialogFragment(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker viewPicker, int hourOfDay, int minute) {
                Snackbar.make(view, String.format("Вы выбрали время: %d часов %d минут", hourOfDay, minute), Snackbar.LENGTH_LONG).show();
            }
        }, 12, 00, true);

        timeDialogFragment.show();
    }

    public void onClickShowDateDialog(View view) {
        MyDateDialogFragment dateDialogFragment = new MyDateDialogFragment(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker viewPicker, int year, int month, int dayOfMonth) {
                Snackbar.make(view, String.format("Вы выбрали дату: %d год %d месяц %d день", year, month + 1, dayOfMonth), Snackbar.LENGTH_LONG).show();
            }
        }, 2024, 2, 15);
        dateDialogFragment.show();
    }

    public void onClickShowProgressDialog(View view) {
        MyProgressDialogFragment progressDialogFragment = new MyProgressDialogFragment(this);
        progressDialogFragment.setTitle("ProgressDialog");
        progressDialogFragment.setMessage("Загрузка...");
        progressDialogFragment.show();
    }
}