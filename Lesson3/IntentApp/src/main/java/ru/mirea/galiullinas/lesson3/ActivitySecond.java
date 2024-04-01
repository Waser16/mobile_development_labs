package ru.mirea.galiullinas.lesson3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ActivitySecond extends AppCompatActivity {

    private TextView textView;
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.textView);

        Intent intent = getIntent();
        String currentTime = intent.getStringExtra("time");

        textView.setText(String.format("Квадрат значения моего номера в списке группы равен %d, а текущее время %s", 36, currentTime));


        Toast.makeText(this, String.format("Квадрат значения моего номера в списке группы равен %d, а текущее время %s", 36, currentTime), Toast.LENGTH_LONG).show();
    }
}