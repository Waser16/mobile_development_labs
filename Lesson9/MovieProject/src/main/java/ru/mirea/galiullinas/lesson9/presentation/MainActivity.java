package ru.mirea.galiullinas.lesson9.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.galiullinas.lesson9.R;
import ru.mirea.galiullinas.lesson9.data.repository.MovieRepositoryImpl;
import ru.mirea.galiullinas.lesson9.domain.models.Movie;
import ru.mirea.galiullinas.lesson9.domain.repository.MovieRepository;
import ru.mirea.galiullinas.lesson9.domain.usecases.GetFavouriteMovieUseCase;
import ru.mirea.galiullinas.lesson9.domain.usecases.SaveMovieToFavouriteUseCase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        EditText editTextEnterFilm = findViewById(R.id.editTextEnterMovie);
        TextView textView = findViewById(R.id.textViewMovie);
        MovieRepository movieRepository = new MovieRepositoryImpl(this);
        findViewById(R.id.btnSaveMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean result = new SaveMovieToFavouriteUseCase(movieRepository).execute(new
                        Movie(2, editTextEnterFilm.getText().toString()));
                textView.setText(String.format("Save result: %s", result));
            }
        });

        findViewById(R.id.btnShowMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movie = new GetFavouriteMovieUseCase(movieRepository).execute();
                textView.setText(String.format("Favourite film: %s", movie.getName()));
            }
        });
    }
}