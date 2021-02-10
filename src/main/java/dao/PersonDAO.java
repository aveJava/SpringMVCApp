package dao;

import models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private int person_COUNT;
    private List<Person> people = new ArrayList<>();

    {
        people.add(new Person(++person_COUNT, "Kate", 19, false));
        people.add(new Person(++person_COUNT, "Jack", 21, true));
        people.add(new Person(++person_COUNT, "Harry", 19, true));
        people.add(new Person(++person_COUNT, "Konor", 22, true));
        people.add(new Person(++person_COUNT, "Amelia", 21, false));
    }

    public Person getPerson(int id) {
        return people.get(id - 1);
    }

    public List<Person> getPeople() {
        return people;
    }
}
