package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import jm.task.core.jdbc.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    //default empty constructor required by the assignment guidelines
    public UserDaoJDBCImpl() {

    }
    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users(" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(50)," +
                "lastName VARCHAR(50)," +
                "age TINYINT)";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }

     @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";

        try (Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

@Override
    public void saveUser(String name, String lastName, byte age) {
String sql = "INSERT INTO users (name, lastName, age) VALUES(?,?,?)";
      try (Connection connection = Util.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(sql)){

        preparedStatement.setString(1, name);
        preparedStatement.setString(2, lastName);
        preparedStatement.setByte(3, age);

        preparedStatement.executeUpdate();
        // Required console output after adding each user
        System.out.println("User with name - " + name + " added to the database");
    } catch (SQLException e) {
        e.printStackTrace();
      }

    }
@Override
    public void removeUserById(long id) {
    String sql = "DELETE FROM users WHERE id = ?";

    try (Connection connection = Util.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


@Override
    public List<User> getAllUsers() {
    List<User> userList = new ArrayList<>();
    String sql = "SELECT * FROM users";

    try (Connection connection = Util.getConnection();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql)) {

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setLastName(resultSet.getString("lastName"));
            user.setAge(resultSet.getByte("age"));

            userList.add(user);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return userList;
}

@Override
    public void cleanUsersTable() {
String sql = "TRUNCATE TABLE users";

try( Connection connection = Util.getConnection();
     Statement statement = connection.createStatement()){
    statement.executeUpdate(sql);
} catch (SQLException e) {
    e.printStackTrace();
}
    }
}
