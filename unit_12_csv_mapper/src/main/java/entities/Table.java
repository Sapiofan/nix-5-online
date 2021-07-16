package entities;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Table {
    private String[] header;
    private List<Person> values;

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public List<Person> getValues() {
        return values;
    }

    public void setValues(List<Person> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return Arrays.equals(header, table.header) &&
                Objects.equals(values, table.values);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(values);
        result = 31 * result + Arrays.hashCode(header);
        return result;
    }
}
