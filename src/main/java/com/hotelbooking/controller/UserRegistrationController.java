package com.hotelbooking.controller;

import com.hotelbooking.model.User;
import com.hotelbooking.service.CrudService;
import com.hotelbooking.service.analysers.AnalyseResult;
import com.hotelbooking.service.analysers.Analyser;

public class UserRegistrationController implements IController<User, Integer>
{
    private final CrudService<User, Integer> crudService;
    private final Analyser<User, Integer> analyser;

    public UserRegistrationController(CrudService<User, Integer> crudService, Analyser<User, Integer> analyser)
    {
        this.crudService = crudService;
        this.analyser = analyser;
    }

    @Override
    public User get(Integer integer)
    {
        return crudService.getById(integer);
    }

    @Override
    public void post(User entity)
    {
        crudService.create(entity);
    }

    @Override
    public void delete(Integer entity)
    {
        crudService.delete(entity);
    }

    @Override
    public AnalyseResult getAnalysis(Integer integer)
    {
        User entity = crudService.getById(integer);
        return analyser.analyse(entity);
    }
}
