package org.chirkov.firstSpringMvcProject.util;

import org.chirkov.firstSpringMvcProject.DataAccessObject.PersonDAO;
import org.chirkov.firstSpringMvcProject.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        //есть ли человек с таким email в базу данных
        if (personDAO.show(person.getEmail()) != null) {
            errors.rejectValue("email","", "This email is already taken");
        }

    }

    public PersonDAO getPersonDAO() {
        return personDAO;
    }
}
