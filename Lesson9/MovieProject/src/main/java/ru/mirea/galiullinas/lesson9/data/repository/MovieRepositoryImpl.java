package ru.mirea.galiullinas.lesson9.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.view.menu.MenuView;

import ru.mirea.galiullinas.lesson9.domain.repository.MovieRepository;
import ru.mirea.galiullinas.lesson9.domain.models.Movie;

public class MovieRepositoryImpl implements MovieRepository {
    private Context context;
    private final String FILE_NAME = "favourite_movie";
    private final String MOVIE_KEY = "favourite_movie";
    private final String DEFAULT_VALUE = "No movie";

    public MovieRepositoryImpl (Context context) {
        this.context = context;
    }
    @Override
    public boolean saveMovie(Movie movie) {
        SharedPreferences sharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(MOVIE_KEY, movie.getName());
        editor.apply();

        return true;
    }

    @Override
    public Movie getMovie() {
        String film = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).
                getString(MOVIE_KEY, DEFAULT_VALUE);
        int id = 1;
        return new Movie(id, film);

    }
}
