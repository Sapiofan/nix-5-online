package entities;

import annotations.Mapper;

import java.util.Objects;

public class Person {
    @Mapper("id")
    private int id;
    @Mapper("name")
    private String name;
    @Mapper("year")
    private String year;
    @Mapper("city")
    private String city;
    @Mapper("mail")
    private String mail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(name, person.name) &&
                Objects.equals(year, person.year) &&
                Objects.equals(city, person.city) &&
                mail.equals(person.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, year, city, mail);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", city='" + city + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
