package ru.mirea.galiullinas.lesson9.domain.usecases;

import ru.mirea.galiullinas.lesson9.domain.models.Movie;
import ru.mirea.galiullinas.lesson9.domain.repository.MovieRepository;

public class SaveMovieToFavouriteUseCase {
    private MovieRepository movieRepository;

    public SaveMovieToFavouriteUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public boolean execute(Movie movie){
        return movieRepository.saveMovie(movie);
    }
}
