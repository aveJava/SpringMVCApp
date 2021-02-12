package dao;

import models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int person_COUNT;   // счетчик для автоинкрементирования id

    private List<Person> people = new ArrayList<>();

    {
        save(new Person("Kate", 19, false));
        save(new Person("Jack", 21, true));
        save(new Person("Harry", 19, true));
        save(new Person("Konor", 22, true));
        save(new Person("Amelia", 21, false));
    }

    public Person getPerson(int id) {
        return people.get(id - 1);
    }

    public List<Person> getPeople() {
        return people;
    }

    public void save(Person person) {
        person.setId(++person_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatePerson) {
        Person person = getPerson(id);
        person.setName(updatePerson.getName());
        person.setAge(updatePerson.getAge());
        person.setSex(updatePerson.getSex());
    }
}
