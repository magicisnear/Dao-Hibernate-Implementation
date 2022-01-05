package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl impl = new UserDaoHibernateImpl();
        impl.createUsersTable();
        impl.saveUser("Jhon", "Karabutovich", (byte) 16);
        impl.saveUser("Ded", "Dedovich", (byte) 48);
        impl.saveUser("Captain", "Morgan", (byte) 25);
        impl.saveUser("Cap", "O4evid", (byte) 36);


        for (User uno : impl.getAllUsers()) {
            System.out.println(uno);
        }

        impl.cleanUsersTable();
        impl.dropUsersTable();
    }
}
