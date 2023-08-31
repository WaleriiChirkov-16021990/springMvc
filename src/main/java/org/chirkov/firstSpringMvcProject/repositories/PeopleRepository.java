package org.chirkov.firstSpringMvcProject.repositories;

import org.chirkov.firstSpringMvcProject.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<Person,Integer> {
    List<Person> findByName(String name);
    Person findPersonByEmail(String email);
}
