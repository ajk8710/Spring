package com.synergisticit.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.User;

@Repository
public class UserDaoImpl implements UserDao {
    
    @Autowired SessionFactory sessionFactory;  // it's LocalSessionFactoryBean entityManagerFactory() in AppConfig.java
    Session session = null;

    @Override
    public User save(User user) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.merge(user);  // use persist() instead of depreciated save() or merge() instead of depreciated saveOrUpdate()
            session.getTransaction().commit();
            return user;
        } catch (Exception ex) {
            System.out.println("Error while opening sesison");
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            users = session.createQuery("from User").list();  // Hibernate Query Language - User is name of class (must start with capital)
        } catch (Exception ex) {
            System.out.println("Error while opening sesison");
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(long userId) {
        User user = null;
        
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            user = session.get(User.class, userId);  // Hibernate Query Language
        } catch (Exception ex) {
            System.out.println("Error while opening sesison");
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public User update(long userId) {
        User user = null;
        
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            user = session.get(User.class, userId);  // Hibernate Query Language
        } catch (Exception ex) {
            System.out.println("Error while opening sesison");
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteById(long userId) {
        User user = null;
        
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            user = session.get(User.class, userId);  // Hibernate Query Language
            if (user != null ) {
                session.delete(user);
                session.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println("Error while opening sesison");
            ex.printStackTrace();
        }
    }

}
