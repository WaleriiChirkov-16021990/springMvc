package org.chirkov.firstSpringMvcProject.DataAccessObject;

import org.chirkov.firstSpringMvcProject.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1Z2x3c4v";
    private static Connection connection;


    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    private static int COUNT_PEOPLE;
//    private final List<Person> people;

//    {
//        people = new ArrayList<>();
//        people.add(new Person(++COUNT_PEOPLE,"Tom","Johns",20, "god@mail.ru"));
//        people.add(new Person(++COUNT_PEOPLE,"Clark","Kent",29, "god1@mail.ru"));
//        people.add(new Person(++COUNT_PEOPLE,"Lao","Czi",28, "god2@mail.ru"));
//        people.add(new Person(++COUNT_PEOPLE,"Hero","Js",27, "god3@mail.ru"));
//        people.add(new Person(++COUNT_PEOPLE,"Java","Utils",26, "god4@mail.ru"));
//        people.add(new Person(++COUNT_PEOPLE,"Development","Kit",25, "god5@mail.ru"));
//        people.add(new Person(++COUNT_PEOPLE,"Java","Runtime",24, "god6@mail.ru"));
//        people.add(new Person(++COUNT_PEOPLE,"Google","Dec",30, "god7@mail.ru"));
//        people.add(new Person(++COUNT_PEOPLE,"Linux","Kali",32, "god8@mail.ru"));
//        people.add(new Person(++COUNT_PEOPLE,"Tod","Shark",35, "god9@mail.ru"));
//        people.add(new Person(++COUNT_PEOPLE,"Jerry","Mouse",40, "god10@mail.ru"));
//        people.add(new Person(++COUNT_PEOPLE,"water","Drop",50, "god11@mail.ru"));
//    }


    public List<Person> index() {
        List<Person> people = new ArrayList<Person>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setSurname(resultSet.getString("surname"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public Person show(int id) {
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        Person person = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            // если много строк показываем только первую строку из выборки

            resultSet.next();

            // либо переходим сразу к заполнению new Person();
            person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setSurname(resultSet.getString("surname"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }


    public void save(Person person) {
//        person.setId(++COUNT_PEOPLE);
//        this.people.add(person);

//        try {
//            Statement statement = connection.createStatement();
//            String SQL = "INSERT INTO person (name,surname, age, email) VALUES ( '"  + person.getName() + "','" + person.getSurname() +"', '" + person.getAge() + "','" + person.getEmail() + "')";
//            statement.executeUpdate(SQL);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO person (name, surname, age, email) VALUES(?,?,?,?)");
            statement.setString(1, person.getName());
            statement.setString(2, person.getSurname());
            statement.setInt(3, person.getAge());
            statement.setString(4, person.getEmail());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(int id, Person updatePerson) {
//        Person personToBeUpdated = show(id);
//        personToBeUpdated.setAge(updatePerson.getAge());
//        personToBeUpdated.setEmail(updatePerson.getEmail());
//        personToBeUpdated.setSurname(updatePerson.getSurname());
//        personToBeUpdated.setName(updatePerson.getName());

        // update person from  database safety

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE person SET name=?, age=?, email=? WHERE id=?");
            preparedStatement.setString(1,updatePerson.getName());
            preparedStatement.setInt(2, updatePerson.getAge());
            preparedStatement.setString(3, updatePerson.getEmail());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
//        people.removeIf(p -> p.getId() == id);


        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM person WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
