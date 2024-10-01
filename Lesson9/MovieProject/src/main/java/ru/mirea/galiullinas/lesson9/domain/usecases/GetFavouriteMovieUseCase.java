package ru.mirea.galiullinas.lesson9.domain.usecases;

import ru.mirea.galiullinas.lesson9.domain.repository.MovieRepository;
import ru.mirea.galiullinas.lesson9.domain.models.Movie;

public class GetFavouriteMovieUseCase {
    private MovieRepository movieRepository;

    public GetFavouriteMovieUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie execute() {
        return movieRepository.getMovie();
    }
}
