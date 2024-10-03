package ru.mirea.galiullinas.musicshop.domain.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private List<Integer> favouriteAlbums;

    public User(int id, String name, String email, String password, List<Integer> favouriteAlbums) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.favouriteAlbums = favouriteAlbums;
    }

    public int getId() {
        return this.id;
    }

    public List<Integer> getFavouriteAlbums() {
        return this.favouriteAlbums;
    }
}
