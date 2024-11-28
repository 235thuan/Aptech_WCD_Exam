package org.example.repository.Impl;

import org.example.entity.Indexer;

import org.example.repository.IndexerRepository;
import org.example.repository.PlayerRepository;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class IndexRepositoryImpl  implements IndexerRepository {
    private SessionFactory sessionFactory;

    // Constructor that initializes the SessionFactory using HibernateUtil
    public IndexRepositoryImpl() {
        // Initialize the sessionFactory using HibernateUtil
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public Indexer save(Indexer indexer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(indexer);
            transaction.commit();
            return indexer;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Optional<Indexer> findById(int indexerId) {
        Session session = sessionFactory.openSession();
        try {
            Indexer indexer = session.get(Indexer.class, indexerId);
            return Optional.ofNullable(indexer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public List<Indexer> findAll() {
        Session session = sessionFactory.openSession();
        try {
            List<Indexer> indexers = session.createQuery("from Indexer", Indexer.class).list();
            return indexers;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean deleteById(int indexerId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Indexer player = session.get(Indexer.class, indexerId);
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
