package ru.mirea.galiullinas.musicshop.domain.usecases;

import java.util.List;

import ru.mirea.galiullinas.musicshop.domain.models.Album;
import ru.mirea.galiullinas.musicshop.domain.repository.AlbumRepository;

public class GetAllAlbumsUseCase {
    private AlbumRepository albumRepository;

    public GetAllAlbumsUseCase(AlbumRepository albumRepository){
        this.albumRepository = albumRepository;
    }

    public List<Album> execute() {
        return albumRepository.getAllAlbums();
    }

}
