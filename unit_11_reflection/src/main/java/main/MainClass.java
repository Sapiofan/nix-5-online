package main;

import entities.Book;
import realization.AppProperties;

public class MainClass {
    public static void main(String[] args) {
        AppProperties props = new AppProperties();
        Book book = props.initialize(Book.class);
        System.out.println(book);
    }
}
