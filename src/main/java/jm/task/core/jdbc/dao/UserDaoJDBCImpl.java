package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.xml.transform.Result;
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
            String SQL = "CREATE TABLE User (\n" +
                    "                      ID int\n" +
                    "    , first_name VARCHAR(30) NOT NULL\n" +
                    "    , last_name VARCHAR(30) NOT NULL\n" +
                    "    , age int\n" +
                    ");";
            statement.executeQuery(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = Util.connection.createStatement();
            String SQL = "DROP TABLE USER";
            statement.executeQuery(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

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

    }
}
