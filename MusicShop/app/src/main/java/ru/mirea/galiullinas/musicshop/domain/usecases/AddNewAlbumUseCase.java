package ru.mirea.galiullinas.musicshop.domain.usecases;

import ru.mirea.galiullinas.musicshop.domain.repository.AlbumRepository;

public class AddNewAlbumUseCase {
    private AlbumRepository albumRepository;
    public AddNewAlbumUseCase(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public void execute(String title, String author, int release, float price) {
        albumRepository.addNewAbum(title, author, release, price);
    }
}
