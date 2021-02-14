package dao;

import controllers.PeopleController;
import models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    // возвращает порядковый номер пользователя в листе people по id пользователя

    public Person getPerson(int id) {
        for (Person person : people) {
            if (person.getId() == id) return person;
        }
        return null;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void save(Person person) {
        person.setId(++person_COUNT);
        people.add(person);
    }

    public void update(Person personToUpdate, Person newPerson) {
        personToUpdate.setName(newPerson.getName());
        personToUpdate.setAge(newPerson.getAge());
        personToUpdate.setSex(newPerson.getSex());
    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }

    public void order(){
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getId() - o2.getId();
            }
        });

        int i = 1;
        for (Person person : people){
            person.setId(i++);
        }

        person_COUNT = people.size() - 1;
    }
}
