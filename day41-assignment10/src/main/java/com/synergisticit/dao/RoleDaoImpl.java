package com.synergisticit.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.Role;

@Repository
public class RoleDaoImpl implements RoleDao {
    
    @Autowired SessionFactory sessionFactory;  // it's LocalSessionFactoryBean entityManagerFactory() in AppConfig.java
    Session session = null;

    @Override
    public Role save(Role role) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.merge(role);  // use persist() instead of depreciated save() or merge() instead of depreciated saveOrUpdate()
            session.getTransaction().commit();
            return role;
        } catch (Exception ex) {
            System.out.println("Error while opening sesison");
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            roles = session.createQuery("from Role").list();  // Hibernate Query Language - Role is name of class (must start with capital)
        } catch (Exception ex) {
            System.out.println("Error while opening sesison");
            ex.printStackTrace();
        }
        return roles;
    }

    @Override
    public Role findById(long roleId) {
        Role role = null;
        
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            role = session.get(Role.class, roleId);  // Hibernate Query Language
        } catch (Exception ex) {
            System.out.println("Error while opening sesison");
            ex.printStackTrace();
        }
        return role;
    }

    @Override
    public Role update(long roleId) {
        Role role = null;
        
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            role = session.get(Role.class, roleId);  // Hibernate Query Language
        } catch (Exception ex) {
            System.out.println("Error while opening sesison");
            ex.printStackTrace();
        }
        return role;
    }

    @Override
    public void deleteById(long roleId) {
        Role role = null;
        
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            role = session.get(Role.class, roleId);  // Hibernate Query Language
            if (role != null ) {
                session.delete(role);
                session.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println("Error while opening sesison");
            ex.printStackTrace();
        }
    }

}
