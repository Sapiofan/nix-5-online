package main;

import collect.CollectObjects;
import entities.Person;
import entities.Table;

import java.util.List;


public class MainClass {
    public static void main(String[] args) {
        CollectObjects objects = new CollectObjects();
        Table table = objects.data();
        List<Person> persons = table.getValues();
        for (Person person : persons) {
            System.out.println(person);
        }
    }
}
