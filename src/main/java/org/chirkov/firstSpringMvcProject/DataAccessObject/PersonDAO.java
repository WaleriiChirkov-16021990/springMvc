package org.chirkov.firstSpringMvcProject.DataAccessObject;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.chirkov.firstSpringMvcProject.models.Person;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {

    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void testNPlus1() {
        try (Session session = entityManager.unwrap(Session.class)) {
            // 1 query from get personList
            List<Person> personList = session.createQuery("select p FROM Person p", Person.class)
                    .getResultList();
            // N query from DataBase
            for (Person person:
                 personList) {
                System.out.println("Person " + person.getName() + " has: " + person.getItemList());
            }

        }
    }



}
