package org.chirkov.firstSpringMvcProject.DataAccessObject;

//import jakarta.persistence.Query;
//import jakarta.transaction.Transactional;
import org.chirkov.firstSpringMvcProject.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.annotation.Transactional;
//import javax.transaction.Transactional;
import java.util.*;

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
        return session.createQuery("select p from Person p", Person.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class,id);
    }

    /*  /////////////////
        перегрузили метод show для поиска совпадений в базе данных по введенному email
        //////////////////////
     */
    @Transactional(readOnly = true)
    public Optional<Person> show(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query<Person> query = session.createQuery("select p from Person p where p.email= :emailUSer", Person.class);
        query.setParameter("emailUSer", email);
        return query.uniqueResultOptional();
    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
//        session.save(String.valueOf(person),Person.class);
        session.save(person);

    }
    @Transactional
    public void update(int id, Person updatePerson) {
        Session session = sessionFactory.getCurrentSession();
        Person personToBeUpdated = session.get(Person.class,id);
        personToBeUpdated.setName(updatePerson.getName());
        personToBeUpdated.setSurname(updatePerson.getSurname());
        personToBeUpdated.setAge(updatePerson.getAge());
        personToBeUpdated.setEmail(updatePerson.getEmail());
        personToBeUpdated.setAddress(updatePerson.getAddress());
    }
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person personToBeDelete = session.get(Person.class,id);
        session.delete(personToBeDelete);
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
