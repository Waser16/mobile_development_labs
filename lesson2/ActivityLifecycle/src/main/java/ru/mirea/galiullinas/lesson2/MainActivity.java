package ru.mirea.galiullinas.lesson2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate just started!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}