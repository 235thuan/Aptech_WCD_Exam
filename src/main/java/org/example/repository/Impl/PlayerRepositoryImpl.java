package org.example.repository.Impl;

import org.example.entity.Player;
import org.example.repository.PlayerRepository;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Optional;

public class PlayerRepositoryImpl implements PlayerRepository {
    private SessionFactory sessionFactory;

    // Constructor that initializes the SessionFactory using HibernateUtil
    public PlayerRepositoryImpl() {
        // Initialize the sessionFactory using HibernateUtil
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public Player save(Player player) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(player);
            transaction.commit();
            return player;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Optional<Player> findById(int playerId) {
        Session session = sessionFactory.openSession();
        try {
            Player player = session.get(Player.class, playerId);
            return Optional.ofNullable(player);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public List<Player> findAll() {
        Session session = sessionFactory.openSession();
        try {
            List<Player> players = session.createQuery("from Player", Player.class).list();
            return players;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean deleteById(int playerId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Player player = session.get(Player.class, playerId);
            if (player != null) {
                session.delete(player);
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
