package ru.mirea.galiullinas.buttonclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textViewStudent;
    private Button btnWhoAmI;
    private Button btnItIsNotMe;
    private CheckBox checkBox;
    public void onItIsNotMeBtnClick(View v) {
        if (checkBox.isChecked())
            checkBox.setChecked(false);
        Toast.makeText(this, "Это не я сделал!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewStudent = findViewById(R.id.tvOut);
        btnWhoAmI = findViewById(R.id.buttonWhoAnI);
        btnItIsNotMe = findViewById(R.id.buttonItIsNotMe);
        checkBox = findViewById(R.id.checkBox);

        View.OnClickListener oclBtnWhoAmI = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewStudent.setText("Мой номер по списку: № 6");
                if (!checkBox.isChecked())
                    checkBox.setChecked(true);
            }
        };
        btnWhoAmI.setOnClickListener(oclBtnWhoAmI);
    }
}