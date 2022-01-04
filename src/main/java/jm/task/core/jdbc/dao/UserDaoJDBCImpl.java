package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            Statement statement = Util.connection.createStatement();
            String SQL = "create table USER ("
                    + "   id INT NOT NULL AUTO_INCREMENT, name VARCHAR(30) NOT NULL, lastname VARCHAR(50) NOT NULL, "
                    + "   age INT, PRIMARY KEY (id) ); ";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = Util.connection.createStatement();
            String SQL = "DROP TABLE USER";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = Util.connection.prepareStatement("INSERT  INTO USER VALUES(?,?,?)");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(1, age);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = Util.connection.prepareStatement("DELETE * FROM USER Where id=?");
            preparedStatement.setInt(1, (int) id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> user = new ArrayList<>();
        try {
            Statement statement = Util.connection.createStatement();
            String SQL = "SELECT * FROM USER";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                User user10 = new User();
                user10.setAge((byte) resultSet.getInt("age"));
                user10.setId((long) resultSet.getInt("id"));
                user10.setName(resultSet.getString("first_name"));
                user10.setLastName(resultSet.getString("last_name"));

                user.add(user10);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = Util.connection.createStatement();
            String SQL = "SELECT * FROM USER";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                removeUserById(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

