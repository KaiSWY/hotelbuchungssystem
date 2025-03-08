package com.hotelbooking.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

abstract class Repository<TEntity, ID>
{
    private final SessionFactory sessionFactory;
    private final Class<TEntity> type;

    public Repository(SessionFactory sessionFactory, Class<TEntity> type)
    {
        this.sessionFactory = sessionFactory;
        this.type = type;
    }

    //TODO error handling
    public void add(TEntity entity)
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try
        {
            session.persist(entity);
            transaction.commit();
            session.close();
        }
        catch (Exception e)
        {
            transaction.rollback();
            throw e;
        }
    }

    public void update(TEntity entity)
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try
        {
            session.merge(entity);
            transaction.commit();
            session.close();
        }
        catch (Exception e)
        {
            transaction.rollback();
            throw e;
        }
    }

    public void delete(TEntity entity)
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try
        {
            session.detach(entity);
            transaction.commit();
            session.close();
        } catch (Exception e)
        {
            transaction.rollback();
            throw e;
        }
    }

    //TODO error handling
    public TEntity getById(ID id)
    {
        Session session = sessionFactory.openSession();
        TEntity entity = session.get(type, id);
        session.close();
        return entity;
    }

    //TODO error handling
    public List<TEntity> getAll()
    {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TEntity> query = builder.createQuery(type);
        query.from(type);
        List<TEntity> result = session.createQuery(query).getResultList();
        session.close();
        return result;
    }
}
