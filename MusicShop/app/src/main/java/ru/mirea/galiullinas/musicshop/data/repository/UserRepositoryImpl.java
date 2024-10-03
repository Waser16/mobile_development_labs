package ru.mirea.galiullinas.musicshop.data.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.mirea.galiullinas.musicshop.domain.models.User;
import ru.mirea.galiullinas.musicshop.domain.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    private List<User> users = Arrays.asList(
            new User(1, "John Doe", "john.doe@example.com", "password123", Arrays.asList(1, 2)),
            new User(2, "Jane Smith", "jane.smith@example.com", "qwerty456", Arrays.asList(3)),
            new User(3, "Alice Johnson", "alice.johnson@example.com", "alice789", Arrays.asList(2, 3)),
            new User(4, "Bob Brown", "bob.brown@example.com", "bobpassword", Arrays.asList(1, 3)),
            new User(5, "Charlie Davis", "charlie.davis@example.com", "charlie456", Arrays.asList(1, 2, 3))
    );
    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public List<Integer> getUserFavouriteAlbums(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user.getFavouriteAlbums();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public boolean signIn(String email, String password) {
        return true;
    }

    @Override
    public boolean signUp(String email, String password, String name) {
        return true;
    }

    @Override
    public boolean addAlbumToFavourites(int userId, int albumId) {
        for (User user : users) {
            if (user.getId() == userId) {
                if (!user.getFavouriteAlbums().contains(albumId)) {
                    user.getFavouriteAlbums().add(albumId);
                    return true;
                }
                return true;
            }
        }
        return false;
    }
}
