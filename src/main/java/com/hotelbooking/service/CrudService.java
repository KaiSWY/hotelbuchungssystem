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


    public void createEntity(T entity)
    {
        repository.add(entity);
    }


    public T getEntityById(ID id)
    {
        return repository.getById(id);
    }


    public void updateEntityById(ID id, T entity)
    {
        T oldEntity = repository.getById(id);
        if (oldEntity != null)
        {
            repository.update(entity);
            return;
        }
        throw new RuntimeException("Entity not found with ID: " + id);
    }


    public void deleteEntityById(ID id)
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


    public List<T> findAllEntities()
    {
        return repository.getAll();
    }
}
