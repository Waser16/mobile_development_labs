package ru.mirea.galiullinas.control_lesson1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_second);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Constraint Layout");

        TextView tvName  = findViewById(R.id.textViewName);
        tvName.setText("Sample Text");

        Button btnContact = findViewById(R.id.buttonContact);
        btnContact.setText("Кноп");
    }
}