package ru.mirea.galiullinas.musicshop.domain.usecases;

import ru.mirea.galiullinas.musicshop.domain.repository.UserRepository;

public class AddAlbumToFavouritesUseCase {
    private UserRepository userRepository;

    public AddAlbumToFavouritesUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(int userId, int albumId) {
        return userRepository.addAlbumToFavourites(userId, albumId);
    }
}
