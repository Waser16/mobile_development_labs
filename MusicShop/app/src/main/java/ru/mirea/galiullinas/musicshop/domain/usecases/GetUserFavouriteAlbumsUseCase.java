package ru.mirea.galiullinas.musicshop.domain.usecases;

import java.util.List;

import ru.mirea.galiullinas.musicshop.domain.models.Album;
import ru.mirea.galiullinas.musicshop.domain.repository.AlbumRepository;
import ru.mirea.galiullinas.musicshop.domain.repository.UserRepository;

public class GetUserFavouriteAlbumsUseCase {
    private UserRepository userRepository;

    public GetUserFavouriteAlbumsUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Integer> execute(int userId) {
        return userRepository.getUserFavouriteAlbums(userId);
    }
}
