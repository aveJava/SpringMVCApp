package dao;

import models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private List<Person> people = new ArrayList<>();

    {
        people.add(new Person( "Kate", 19, false));
        people.add(new Person( "Jack", 21, true));
        people.add(new Person( "Harry", 19, true));
        people.add(new Person( "Konor", 22, true));
        people.add(new Person( "Amelia", 21, false));
    }

    public Person getPerson(int id) {
        return people.get(id - 1);
    }

    public List<Person> getPeople() {
        return people;
    }

    public void save(Person person) {
        people.add(person);
    }
}
