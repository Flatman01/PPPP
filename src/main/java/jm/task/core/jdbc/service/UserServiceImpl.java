package jm.task.core.jdbc.service;

import com.sun.xml.bind.v2.model.core.ID;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserServiceImpl implements UserService {

    Connection connection;

    private UserDaoJDBCImpl userDao;


    public UserServiceImpl() {
    }

    public void createUsersTable() {

        userDao.createUsersTable();

    }

    public void dropUsersTable() {

        userDao.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {

        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection1 = getConnection()) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем" + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection connection1 = getConnection()) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public List<User> getAllUsers() {

        List<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM users";
        Statement statement = null;
        try (Connection connection1 = getConnection()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("Age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public void cleanUsersTable() {

        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM users";
        try (Connection connection1 = getConnection()){
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
