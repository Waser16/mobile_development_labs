package ru.mirea.galiullinas.lesson9.domain.repository;

import ru.mirea.galiullinas.lesson9.domain.models.Movie;

public interface MovieRepository {
    public boolean saveMovie(Movie movie);
    public Movie getMovie();
}
