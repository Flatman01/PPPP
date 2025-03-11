package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
            String sqlCommand = "CREATE TABLE IF NOT EXISTS username ("
                    + "id SERIAL PRIMARY KEY,"
                    + "name VARCHAR(255),"
                    + "lastname VARCHAR(255),"
                    + "age VARCHAR(255))";
            try (Connection connection = getConnection();) {
                Statement statement = connection.createStatement();
                statement.executeUpdate(sqlCommand);
                System.out.println("Таблица users создана");
        } catch (Exception ex) {
            System.out.println("Ошибка создания таблицы users" + ex.getMessage());
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS username";
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Таблица users удалена");
        } catch (SQLException e) {
            System.out.println("Ошибка удаления таблицы users" + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO username "
                + "(name, lastname, age) "
                + "VALUES (?, ?, ?)";
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, String.valueOf(age));
            preparedStatement.executeUpdate();
            System.out.println("Пользователь " + name + " " + lastName + " " + age + " в таблицу user добавлен");
        } catch (SQLException e) {
            System.out.println("Ошибка сохранения пользоватей в таблицу " + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM username WHERE id = " + id;
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Пользователь" + id + "удален");
        } catch (SQLException e) {
            System.out.println("Ошибка при удаление пользователя " + id + " " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM username";
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setAge(resultSet.getByte("age"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                users.add(user);
            }
            System.out.println("Все пользователи БД users получены");
        } catch (SQLException e) {
            System.out.println("Ошибка при получение всех пользователей БД users" + e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM username";
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Все пользователи удалены");
        } catch (SQLException e) {
            System.out.println("Ошибка при удаление всех пользователей " + e.getMessage());
        }
    }
}