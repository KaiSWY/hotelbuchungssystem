package com.hotelbooking.repository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public abstract class Repository<TEntity, ID> implements IRepository<TEntity, ID>
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
        try (Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            try
            {
                session.persist(entity);
                transaction.commit();
            } catch (Exception e)
            {
                transaction.rollback();
                throw e;
            }
        }
    }

    public void update(TEntity entity)
    {
        try (Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            try
            {
                session.merge(entity);
                transaction.commit();
            } catch (Exception e)
            {
                transaction.rollback();
                //TODO error message
            }
        }
    }

    public void deleteById(ID id)
    {
        try (Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            try
            {
                TEntity entity = session.get(type, id);
                if (entity != null)
                {
                    session.remove(entity);
                    transaction.commit();
                }
                else
                {
                    throw new EntityNotFoundException("Entity with ID " + id + " not found.");
                }
            } catch (Exception e)
            {
                transaction.rollback();
                //TODO error message
            }
        }
    }

    public TEntity getById(ID id)
    {
        try (Session session = sessionFactory.openSession())
        {
            TEntity entity = session.get(type, id);
            if (entity == null)
            {
                throw new EntityNotFoundException("Entity with ID " + id + " not found.");
            }
            EntityLazyLoader.initializeEntity(session, entity);
            return entity;
        }
    }

    public List<TEntity> getAll()
    {
        try (Session session = sessionFactory.openSession())
        {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TEntity> query = builder.createQuery(type);
            query.from(type);
            List<TEntity> results = session.createQuery(query).getResultList();
            for (TEntity entity : results)
            {
                EntityLazyLoader.initializeEntity(session, entity);
            }
            return results;
        }
    }
}
