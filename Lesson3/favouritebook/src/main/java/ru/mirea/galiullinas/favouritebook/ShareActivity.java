package ru.mirea.galiullinas.favouritebook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ShareActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editTextText);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String favouriteBook = extras.getString(MainActivity.KEY);
            textView.setText(String.format("Моя любимая книга: %s", favouriteBook));

        }
    }

    public void onClickChangeBook(View view) {
        Intent data = new Intent();
        if(editText.getText().length() > 0) {
            data.putExtra(MainActivity.USER_MESSAGE, editText.getText().toString());
            setResult(Activity.RESULT_OK, data);
        }
        else {
            setResult(Activity.RESULT_CANCELED, data);
        }
        finish();
    }
}