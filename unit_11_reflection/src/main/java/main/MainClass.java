package main;

import entities.Book;
import realization.AppProperties;

public class MainClass {
    public static void main(String[] args) {
        Book book = new Book();
        AppProperties props = new AppProperties();
        props.initialize(book);
        System.out.println(book);
    }
}
