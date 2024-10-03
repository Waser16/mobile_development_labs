package ru.mirea.galiullinas.musicshop.domain.repository;

import java.util.List;

import ru.mirea.galiullinas.musicshop.domain.models.User;

public interface UserRepository {
    List<User> getAllUsers();
    List<Integer> getUserFavouriteAlbums(int userId);
    boolean signIn(String email, String password);
    boolean signUp(String email, String password, String name);
    boolean addAlbumToFavourites(int userId, int albumId);

}
