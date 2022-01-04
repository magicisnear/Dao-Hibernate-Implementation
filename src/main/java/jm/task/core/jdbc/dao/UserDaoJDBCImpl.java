package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static int id = 0;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            Statement statement = Util.connection.createStatement();
            String SQL = "create table if not exists USER ("
                    + "   id INT AUTO_INCREMENT, name VARCHAR(30) NOT NULL, lastname VARCHAR(50) NOT NULL, "
                    + "   age INT, PRIMARY KEY (id) ); ";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = Util.connection.createStatement();
            String SQL = "DROP TABLE IF EXISTS USER";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = Util.connection.prepareStatement("INSERT  INTO USER VALUES(?,?,?,?)");

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setInt(4, age);

            preparedStatement.executeUpdate();

            System.out.printf("User с именем – %s добавлен в базу данных \n", name);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = Util.connection.prepareStatement("DELETE FROM USER WHERE id=?");
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
                user10.setName(resultSet.getString("name"));
                user10.setLastName(resultSet.getString("lastname"));

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

