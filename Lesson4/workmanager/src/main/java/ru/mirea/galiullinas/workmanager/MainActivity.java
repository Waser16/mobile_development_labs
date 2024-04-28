package ru.mirea.galiullinas.workmanager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.Worker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WorkRequest uploadWorkRequest =
                new OneTimeWorkRequest.Builder(UploadWorker.class)
                        .build();
        WorkManager.getInstance(this).enqueue(uploadWorkRequest);

//        Constraints constraints = new Constraints.Builder()
//                .setRequiredNetworkType(NetworkType.UNMETERED)
//                .setRequiresCharging(true)
//                .build();
//
////        uploadWorkRequest =
////                new OneTimeWorkRequest.Builder(UploadWorker.class)
////                        .setConstraints(constraints)
////                        .build();

    }
}