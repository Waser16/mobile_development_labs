package ru.mirea.galiullinas.musicshop.domain.repository;

import java.util.List;

import ru.mirea.galiullinas.musicshop.domain.models.Album;

public interface AlbumRepository {
    List<Album> getAllAlbums();
    Album getAlbumById(int id);
    List<Album> getAlbumsByTitle(String title);
    boolean addNewAbum(String title, String author, int release, float price);
    // boolean addAlbumToFavourites(Album album);

    //List<Album> getUserFavouriteAlbums(int userId);

}
