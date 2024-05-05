package ru.mirea.galiullinas.mireaproject.ui.camera;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.galiullinas.mireaproject.R;

public class ProfileDataActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextAbout;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_data);

        editTextName = findViewById(R.id.textViewNameResult);
        editTextAge = findViewById(R.id.textViewAgeResult);
        editTextAbout = findViewById(R.id.textViewAboutResult);
        button = findViewById(R.id.button);
    }

    public void sendData(View view) {
        Intent data = new Intent();
        data.putExtra("name", editTextName.getText().toString());
        data.putExtra("age", editTextAge.getText().toString());
        data.putExtra("about", editTextAbout.getText().toString());
        setResult(Activity.RESULT_OK, data);
        finish();
    }

}