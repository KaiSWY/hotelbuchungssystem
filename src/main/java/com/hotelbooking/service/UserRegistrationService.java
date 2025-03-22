package com.hotelbooking.service;

import com.hotelbooking.model.User;
import com.hotelbooking.repository.IRepository;

public class UserRegistrationService extends CrudService<User, Integer>
{
    public UserRegistrationService(IRepository<User, Integer> repository)
    {
        super(repository);
    }
}
