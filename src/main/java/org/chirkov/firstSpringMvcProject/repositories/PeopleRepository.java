package org.chirkov.firstSpringMvcProject.repositories;

import org.chirkov.firstSpringMvcProject.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    //    поиск только по полю класса Person
    List<Person> findByEmail(String email);

    //   поиск по имени
    List<Person> findByName(String name);

    //    поиск по имени с сортировкой по возрастанию возраста
    List<Person> findByNameOrderByAge(String name);

    //    поиск по первым символам строки имени
    List<Person> findByNameStartingWith(String startingWith);

    //    поиск по имени или почте
    List<Person> findByNameOrEmail(String name, String email);


}
