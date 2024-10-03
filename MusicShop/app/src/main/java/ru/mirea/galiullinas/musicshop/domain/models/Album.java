package ru.mirea.galiullinas.musicshop.domain.models;

public class Album {

    private int id;
    private String title;
    private String author;
    private int releaseYear;
    private float price;
    public Album(int id, String title, String author, int releaseYear, float price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.releaseYear = releaseYear;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", releaseYear=" + releaseYear +
                ", price=" + price +
                "}\n";
    }
}
