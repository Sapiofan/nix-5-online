package entities;

import java.util.List;
import java.util.Objects;

public class Table {
    private List<String> header;
    private List<Person> people;
    private List<String[]> values;

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public List<String[]> getValues() {
        return values;
    }

    public void setValues(List<String[]> values) {
        this.values = values;
    }

    public String cell(int row, int column){
        if(indexCorrect(row, column)){
            String[] r = values.get(row);
            return r[column-1];
        }
        return "wrong indexes";
    }

    public String cell(int row, String Header){
        if(checkRowHeader(row, Header)){
            int column = header.indexOf(Header);
            String[] r = values.get(row);
            return r[column];
        }
        return "wrong input";
    }

    private boolean indexCorrect(int row, int column){
        if((row <= 0 || row > values.size()) && (column <= 0 || column > values.get(row).length)){
            return false;
        }
        return true;
    }

    private boolean checkRowHeader(int row, String Header){
        if(row <= 0 || row > values.size()) return false;
        for (String s : header) {
            if(s.equalsIgnoreCase(Header)) return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return Objects.equals(header, table.header) &&
                Objects.equals(people, table.people) &&
                Objects.equals(values, table.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, people, values);
    }
}
