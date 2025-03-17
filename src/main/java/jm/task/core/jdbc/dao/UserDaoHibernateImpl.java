package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.HibernateUtil;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sqlCommand = "CREATE TABLE IF NOT EXISTS username ("
                + "id SERIAL PRIMARY KEY,"
                + "name VARCHAR(255),"
                + "lastname VARCHAR(255),"
                + "age VARCHAR(255))";
        Query query = session.createSQLQuery(sqlCommand);
        transaction.commit();
        session.close();
    }
    @Transactional
    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();


    }
    @Transactional
    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        session.save(new User(name, lastName, age));

    }
    @Transactional()
    @Override()
    public void removeUserById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(User.class, id));
    }
    @Transactional
    @Override
    public List<User> getAllUsers() {
    Session session = sessionFactory.getCurrentSession();
    List<User> users = session.createQuery("from User").list();
    return users;
    }
    @Transactional
    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete from User").executeUpdate();

    }
}
