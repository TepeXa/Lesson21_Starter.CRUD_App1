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
        Person person = null;
        //PreparedStatement preparedStatement = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Person WHERE id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setEmail(resultSet.getString("email"));
            person.setAge(resultSet.getInt("age"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    public void save (Person person){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Person VALUES(1,?,?,?)");
            preparedStatement.setString(1,person.getName());
            preparedStatement.setInt(2,person.getAge());
            preparedStatement.setString(3,person.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void update(int id, Person updatePerson) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Person SET name=?,age=?,email=? WHERE id =?");
            preparedStatement.setString(1,updatePerson.getName());
            preparedStatement.setInt(2,updatePerson.getAge());
            preparedStatement.setString(3,updatePerson.getEmail());
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE Person FROM Person WHERE id =?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
