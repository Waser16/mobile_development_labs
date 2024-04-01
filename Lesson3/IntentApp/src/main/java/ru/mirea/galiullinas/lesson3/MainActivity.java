package ru.mirea.galiullinas.lesson3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private String getCurrentTime() {
        long dateInMilis = System.currentTimeMillis();
        String format = "yyyy-MM-dd HH:mm:ss";
        final SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateString = sdf.format(new Date(dateInMilis));

        return dateString;
    }


    public void onClickSendInfo(View v) {
        Intent intent = new Intent(this, ActivitySecond.class);
        String info = getCurrentTime();
        intent.putExtra("time", info);
        startActivity(intent);
    }
}