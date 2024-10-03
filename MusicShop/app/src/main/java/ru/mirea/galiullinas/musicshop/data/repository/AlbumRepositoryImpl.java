package ru.mirea.galiullinas.musicshop.data.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.mirea.galiullinas.musicshop.domain.models.Album;
import ru.mirea.galiullinas.musicshop.domain.models.User;
import ru.mirea.galiullinas.musicshop.domain.repository.AlbumRepository;

public class AlbumRepositoryImpl implements AlbumRepository {
    private List<Album> albums = new ArrayList<> (
            Arrays.asList(
                    new Album(1, "Symbolic", "Death", 1995, 10.99f),
                new Album(2, "Age of Excues", "Mgla", 2016, 9.99f),
                new Album(3, "Ride The Lightning", "Metallica", 1984, 11.99f)
            )
    );

    @Override
    public List<Album> getAllAlbums() {
        return albums;
    }

    @Override
    public Album getAlbumById(int id) {
        for (Album album : albums) {
            if (album.getId() == id) {
                return album;
            }
        }
        return null;
    }

    @Override
    public List<Album> getAlbumsByTitle(String title) {
        List<Album> result = new ArrayList<>();
        for (Album album : albums) {
            if (album.getTitle().equalsIgnoreCase(title)) {
                result.add(album);
            }
        }
        return result;
    }

    @Override
    public boolean addNewAbum(String title, String author, int release, float price) {
        Album newAlbun = new Album(this.albums.size() + 1, title,
                author, release, price);
        this.albums.add(newAlbun);;
        return true;
    }

//    @Override
//    public boolean addAlbumToFavourites(Album album) {
//        return false;
//    }
//
//    @Override
//    public List<Album> getUserFavouriteAlbums(int userId) {
//        for (User user : users) {
//            if (user.getId() == userId) {
//                // Получаем список ID любимых альбомов пользователя
//                List<Integer> favouriteAlbumIds = user.getFavouriteAlbums();
//
//                // Возвращаем список альбомов, соответствующих этим ID
//                List<Album> favouriteAlbums = new ArrayList<>();
//                for (Album album : albums) {
//                    if (favouriteAlbumIds.contains(album.getId())) {
//                        favouriteAlbums.add(album);
//                    }
//                }
//                return favouriteAlbums;
//            }
//        }
//        // Если пользователь не найден, возвращаем пустой список
//        return new ArrayList<>();
//    }
}
