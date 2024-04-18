package ru.mirea.galiullinas.serviceapp;

import static android.Manifest.permission.FOREGROUND_SERVICE;
import static android.Manifest.permission.POST_NOTIFICATIONS;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.TimeUnit;

import ru.mirea.galiullinas.serviceapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private int permissionCode = 200;
    private boolean playing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            Log.d(MainActivity.class.getSimpleName().toString(), "Разрешения получены");
        }
        else {
            Log.d(MainActivity.class.getSimpleName().toString(), "Разрешений не получено");
            ActivityCompat.requestPermissions(this, new String[] {POST_NOTIFICATIONS, FOREGROUND_SERVICE},
                    permissionCode);
        }

        binding.btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public	void	onClick(View	v)	{
                if (playing) {
                    stopService(
                            new  Intent(MainActivity.this,  PlayerService.class));
                } else {
                    Intent serviceIntent  =  new  Intent(MainActivity.this,  PlayerService.class);
                    ContextCompat.startForegroundService(MainActivity.this,  serviceIntent);
                }

                playing = !playing;
                if (playing) {
                    binding.btnPlayPause.setImageDrawable(
                            getResources().getDrawable(R.drawable.player_pause));
                }
                else {
                    binding.btnPlayPause.setImageDrawable(
                            getResources().getDrawable(R.drawable.player_play));
                }
            }
        });

        /*
        binding.btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, PlayerService.class));
            }
        });
        */
    }
}