package org.chirkov.firstSpringMvcProject.DataAccessObject;

import org.chirkov.firstSpringMvcProject.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private static int COUNT_PEOPLE;
    private final List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++COUNT_PEOPLE,"Tom","Johns",20, "god@mail.ru"));
        people.add(new Person(++COUNT_PEOPLE,"Clark","Kent",29, "god1@mail.ru"));
        people.add(new Person(++COUNT_PEOPLE,"Lao","Czi",28, "god2@mail.ru"));
        people.add(new Person(++COUNT_PEOPLE,"Hero","Js",27, "god3@mail.ru"));
        people.add(new Person(++COUNT_PEOPLE,"Java","Utils",26, "god4@mail.ru"));
        people.add(new Person(++COUNT_PEOPLE,"Development","Kit",25, "god5@mail.ru"));
        people.add(new Person(++COUNT_PEOPLE,"Java","Runtime",24, "god6@mail.ru"));
        people.add(new Person(++COUNT_PEOPLE,"Google","Dec",30, "god7@mail.ru"));
        people.add(new Person(++COUNT_PEOPLE,"Linux","Kali",32, "god8@mail.ru"));
        people.add(new Person(++COUNT_PEOPLE,"Tod","Shark",35, "god9@mail.ru"));
        people.add(new Person(++COUNT_PEOPLE,"Jerry","Mouse",40, "god10@mail.ru"));
        people.add(new Person(++COUNT_PEOPLE,"water","Drop",50, "god11@mail.ru"));
    }


    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }


    public void save(Person person) {
        person.setId(++COUNT_PEOPLE);
        this.people.add(person);
    }

    public void update(int id, Person updatePerson) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setAge(updatePerson.getAge());
        personToBeUpdated.setEmail(updatePerson.getEmail());
        personToBeUpdated.setSurname(updatePerson.getSurname());
        personToBeUpdated.setName(updatePerson.getName());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }


}
