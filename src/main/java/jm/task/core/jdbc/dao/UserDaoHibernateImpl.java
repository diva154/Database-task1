package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50)," + "lastName VARCHAR(50),"+ "age TINYINT)";

        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        String sql = "DROP TABLE IF EXISTS users";
        try(Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }}

    @Override
    public void saveUser(String name, String lastName, byte age){
            Transaction transaction = null;
            try(Session session = Util.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                User user = new User(name,lastName, age);
                session.save(user); //automatically generates the INSERT query
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null){
                    transaction.rollback();
            }
                e.printStackTrace();
            } }

    @Override
    public void removeUserById(long id){
                Transaction transaction = null;
                try (Session session = Util.getSessionFactory().openSession()) {
                    transaction = session.beginTransaction();
                    User user = session.get(User.class, id);
                    if (user != null) {
                        session.delete(user); //automatically generates DELETE by id
                    }
                    transaction.commit();
                } catch (Exception e) {
                    if (transaction != null) {
                        transaction.rollback();
                    }
                    e.printStackTrace();
                }}


    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            //"User" refers to the java entity class name , not table name
            users = session.createQuery("from User ", User.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return users;
        //returns empty list instead of null if empty or failed
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try(Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
                transaction.commit();
            } catch (Exception e){
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
            }
        }

