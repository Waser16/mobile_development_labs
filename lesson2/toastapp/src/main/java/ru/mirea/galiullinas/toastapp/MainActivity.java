package ru.mirea.galiullinas.toastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextText);
    }

    public void showToast(View view) {
        // String inputText = (String) editText.getText().length();
        @SuppressLint("DefaultLocale") Toast toast = Toast.makeText(getApplicationContext(),
                String.format("СТУДЕНТ №%d ГРУППА %s Количество символов - %d", 6, "БСБО-10-21",
                        editText.getText().length()),
                Toast.LENGTH_SHORT);
        toast.show();
    }
}