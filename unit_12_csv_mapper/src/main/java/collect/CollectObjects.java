package collect;

import entities.Person;
import entities.Table;
import reading.CSVreader;
import service.Mapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CollectObjects {
    public Table data(){
        Mapping mapping = new Mapping();
        Table table = new Table();
        CSVreader reader = new CSVreader();
        List<String[]> values = reader.read();
        table.setValues(values);
        List<Person> persons = new ArrayList<>();
        int counter = 0;
        List<String> header = new ArrayList<>();
        HashMap<String, Integer> h = new HashMap<>();
        int index = 0;
        for (String[] row : values) {
            if(counter == 0){
                for (String s : row) {
                    header.add(s);
                    h.put(s, index++);
                }
                table.setHeader(header);
                counter++;
                continue;
            }
            Person person = mapping.mapper(Person.class, row, h);
            persons.add(person);
        }
        table.setPeople(persons);
        return table;
    }
}
