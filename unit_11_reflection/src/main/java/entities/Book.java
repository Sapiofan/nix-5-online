package entities;

import annotations.PropertyKey;

public class Book {
    @PropertyKey("name")
    private String name;
    @PropertyKey("author")
    private String author;
    @PropertyKey("year")
    private int year;
    @PropertyKey("genre")
    private String genre;

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                '}';
    }
}
