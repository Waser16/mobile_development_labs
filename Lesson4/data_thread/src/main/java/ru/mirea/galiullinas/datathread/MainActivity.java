package ru.mirea.galiullinas.datathread;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.TimeUnit;

import ru.mirea.galiullinas.datathread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final Runnable RUN_1 = new Runnable() {
            @Override
            public void run() {
                binding.textView.setText("RUN_1");
            }
        };

        final Runnable RUN_2 = new Runnable() {
            @Override
            public void run() {
                binding.textView.setText("RUN_2");
            }
        };

        final Runnable RUN_3 = new Runnable() {
            @Override
            public void run() {
                binding.textView.setText("RUN_3");
            }
        };

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(RUN_1);
                    TimeUnit.SECONDS.sleep(1);
                    binding.textView.postDelayed(RUN_3, 2000);
                    binding.textView.post(RUN_2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}