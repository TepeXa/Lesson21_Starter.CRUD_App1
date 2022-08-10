package ru.alishev.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private static final String URL ="jdbc:mysql://localhost:3306/new_schema_test";
    private static final String USERNAME = "Yanewuser";
    private static final String PASSWORD = "Yanewuser!";
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver"); //com.mysql.jdbc.Driver  //com.mysql.cj.jdbc.Driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
  //  private List<Person> people;
  //  {
  //      people = new ArrayList<>();
  //      people.add(new Person(++PEOPLE_COUNT,"Den", 37, "den@mail.ru"));
  //      people.add(new Person(++PEOPLE_COUNT,"Miron",5,"miron@mail.ru"));
  //      people.add(new Person(++PEOPLE_COUNT,"Anastasia", 31, "nastya@mail.ru"));
  //      people.add(new Person(++PEOPLE_COUNT,"Matvei",2, "matvei@mail.ru"));
  //  }

    public List<Person> index(){
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Person person = new Person ();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setAge(resultSet.getInt("age"));
                people.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    public Person show(int id) {
  //      return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        return null;
    }

    public void save (Person person){
   //     person.setId(++PEOPLE_COUNT);
    //    people.add(person);
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO PERSON VALUES (" + 1 + ",'" + person.getName() +
                    "'," + person.getAge() + ",'" + person.getEmail() + "')";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void update(int id, Person updatePerson) {
//        Person personToBeUpdated = show(id);
//        personToBeUpdated.setName(updatePerson.getName());
 //       personToBeUpdated.setAge(updatePerson.getAge());
//        personToBeUpdated.setEmail(updatePerson.getEmail());
    }
    public void delete(int id) {
 //       people.removeIf(p->p.getId()==id);
    }
}
