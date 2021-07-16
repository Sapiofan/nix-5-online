package collect;

import entities.Person;
import entities.Table;
import reading.CSVreader;
import service.Mapping;

import java.util.ArrayList;
import java.util.List;

public class CollectObjects {
    public Table data(){
        Mapping mapping = new Mapping();
        Table table = new Table();
        CSVreader reader = new CSVreader();
        List<String[]> values = reader.read();
        List<Person> persons = new ArrayList<>();
        int counter = 0;
        for (String[] row : values) {
            if(counter == 0){
                table.setHeader(row);
                counter++;
                continue;
            }
            Person person = mapping.mapper(Person.class, row);
            persons.add(person);
        }
        table.setValues(persons);
        return table;
    }
}
