package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        HibernateUtil.sessionFactory();

        UserServiceImpl userService = new UserServiceImpl();


        userService.createUsersTable();

        userService.saveUser("Ivan", "Ivanov", (byte) 10);
        userService.saveUser("Mariya", "Ivanova", (byte) 20);
        userService.saveUser("Nikolay", "Ivanov", (byte) 30);
        userService.saveUser("Svetlana", "Ivanova", (byte) 40);

        userService.getAllUsers();

        userService.cleanUsersTable();

       userService.dropUsersTable();

    }

}
