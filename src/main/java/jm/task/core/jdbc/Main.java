package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Connection connection = Util.getConnection();
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 10);
        userService.saveUser("Mariya", "Ivanova", (byte) 20);
        userService.saveUser("Nikolay", "Ivanov", (byte) 30);
        userService.saveUser("Svetlana", "Ivanova", (byte) 40);

        userService.getAllUsers();

        userService.dropUsersTable();

        userService.cleanUsersTable();

    }

}
