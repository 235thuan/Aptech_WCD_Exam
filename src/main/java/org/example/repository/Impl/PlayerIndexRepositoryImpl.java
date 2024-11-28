package org.example.repository.Impl;

import org.example.entity.PlayerIndex;
import org.example.repository.PlayerIndexRepository;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class PlayerIndexRepositoryImpl implements PlayerIndexRepository {
    private SessionFactory sessionFactory;

    // Constructor that initializes the SessionFactory using HibernateUtil
    public PlayerIndexRepositoryImpl() {
        // Initialize the sessionFactory using HibernateUtil
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public PlayerIndex save(PlayerIndex playerIndex) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(playerIndex);
            transaction.commit();
            return playerIndex;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Optional<PlayerIndex> findById(int playerIndexId) {
        Session session = sessionFactory.openSession();
        try {
            PlayerIndex playerIndex = session.get(PlayerIndex.class, playerIndexId);
            return Optional.ofNullable(playerIndex);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public List<PlayerIndex> findAll() {
        Session session = sessionFactory.openSession();
        try {
            List<PlayerIndex> playerIndexs = session.createQuery("from PlayerIndex", PlayerIndex.class).list();
            return playerIndexs;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean deleteById(int playerIndexId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            PlayerIndex playerIndex = session.get(PlayerIndex.class, playerIndexId);
            if (playerIndex != null) {
                session.delete(playerIndex);
                transaction.commit();
                return true;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }
}
