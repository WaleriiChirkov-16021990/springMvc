package org.chirkov.firstSpringMvcProject.DataAccessObject;

import org.chirkov.firstSpringMvcProject.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * @author : @Valerii_Chirkov $tlg
 * This class is responsible for PostgreSQL 15
 */
@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();

//        обычный hibernate код
        List<Person> personList = session.createQuery("select p from Person p", Person.class).getResultList();
        return personList;
    }

    public Person show(int id) {
        return null;
    }

    /*  /////////////////
        перегрузили метод show для поиска совпадений в базе данных по введенному email
        //////////////////////
     */
    public Optional<Person> show(String email) {
        return null;
    }


    public void save(Person person) {

    }

    public void update(int id, Person updatePerson) {

    }

    public void delete(int id) {
    }


    ////////////////////////////////////////////////////////////////
    ////////// Тестируем производительность пакетной вставки
    ////////////////////////////////........../////////////////

    public void batchUpdate() {
        List<Person> people = createCountPeople(200);
        long start = System.currentTimeMillis();
//        jdbcTemplate.batchUpdate("INSERT INTO person (name, surname, age, email, address) VALUES (?,?,?,?,?)"
//                , new BatchPreparedStatementSetter() {
//                    @Override
//                    public void setValues(PreparedStatement ps, int i) throws SQLException {
//                        ps.setString(1, people.get(i).getName());
//                        ps.setString(2, people.get(i).getSurname());
//                        ps.setInt(3, people.get(i).getAge());
//                        ps.setString(4, people.get(i).getEmail());
//                        ps.setString(5, people.get(i).getAddress());
//                    }
//
//                    @Override
//                    public int getBatchSize() {
//                        return people.size();
//                    }
//                });
        long end = System.currentTimeMillis();
        System.out.printf("Time batchUpdate: %d", (end - start) / 1000);
    }

    public void testMultipleUpdate() {
        List<Person> people = createCountPeople(100);
        long start_before = System.currentTimeMillis();
        for (Person person : people
        ) {
//            jdbcTemplate.update("INSERT INTO person (name, surname, age, email, address) VALUES (?,?,?,?,?)"
//                    , person.getName(), person.getSurname(), person.getAge(), person.getEmail(), person.getAddress());
        }
        long end_after = System.currentTimeMillis();
        System.out.printf("Время обновлвения поочереди = %d", (end_after - start_before) / 1000);
    }

    private List<Person> createCountPeople(int count) {
        List<Person> people = new ArrayList<Person>();
        Random random = new Random();
//        for (int i = 0; i < count; i++) {
//            people.add(new Person(i, "John" + String.valueOf(i), "Doe" + String.valueOf(i)
//                    , random.nextInt(4, 120), "john" + String.valueOf(i) + ".doe@gmail.com", "Russian, Tver, 170039"));
//        }
//        System.out.printf(String.valueOf(people.size()));
        return people;
    }
}
