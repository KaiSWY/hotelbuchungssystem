package com.hotelbooking.service;

import com.hotelbooking.repository.IRepository;

import java.util.List;

public abstract class CrudService<T, ID>
{
    protected final IRepository<T, ID> repository;

    protected CrudService(IRepository<T, ID> repository)
    {
        this.repository = repository;
    }


    public void create(T entity)
    {
        repository.add(entity);
    }


    public T getById(ID id)
    {
        return repository.getById(id);
    }


    public void update(ID id, T entity)
    {
        T oldEntity = repository.getById(id);
        if (oldEntity != null)
        {
            repository.update(entity);
        }
        throw new RuntimeException("Entity not found with ID: " + id);
    }


    public void delete(ID id)
    {
        T oldEntity = repository.getById(id);
        if (oldEntity != null)
        {
            repository.deleteById(id);
        }
        else
        {
            throw new RuntimeException("Entity not found with ID: " + id);
        }
    }


    public List<T> findAll()
    {
        return repository.getAll();
    }
}
