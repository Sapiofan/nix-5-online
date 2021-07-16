package main;

import collect.CollectObjects;
import entities.Person;
import entities.Table;

import java.util.List;


public class MainClass {
    public static void main(String[] args) {
        CollectObjects objects = new CollectObjects();
        Table table = objects.data();
        List<Person> persons = table.getPeople();
        for (Person person : persons) {
            System.out.println(person);
        }

        System.out.println(table.cell(2, 4));

        System.out.println(table.cell(1, "name"));

        System.out.println("\nHeaders");
        for (String s : table.getHeader()) {
            System.out.println(s);
        }
    }
}
