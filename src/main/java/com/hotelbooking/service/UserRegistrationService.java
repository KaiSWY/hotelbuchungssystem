package com.hotelbooking.service;

import com.hotelbooking.model.User;
import com.hotelbooking.repository.IUserRepository;

import java.util.List;

public class UserRegistrationService
{
    private final IUserRepository userRepository;

    public UserRegistrationService(IUserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    //TODO error handling
    public void registerUser(String firstName, String lastName, String email, int postalCode, String city, String street, int houseNumber)
    {
        User registeredUser = new User(
                firstName,
                lastName,
                email,
                postalCode,
                city,
                street,
                houseNumber
        );

        if (!isRegisterUserConflict(registeredUser))
        {
            //register new user
            this.userRepository.add(registeredUser);
        }
        else
        {
            throw new IllegalArgumentException("User already exists!");
        }
    }

    private boolean isRegisterUserConflict(User registeredUser)
    {
        List<User> allRegisteredUsers = this.userRepository.getAll();
        return allRegisteredUsers.contains(registeredUser);
    }

    //TODO error handling
    public void updateUser(User user, User updatedUser) {
        if (userExists(user))
        {
            //update user
            this.userRepository.update(updatedUser);
        }
        else
        {
            throw new IllegalArgumentException("User does not exists!");
        }
    }

    //TODO error handling
    public void deleteUser(User user) {
        if (userExists(user)) {
            //delete user
            this.userRepository.delete(user);
        }
        else
        {
            throw new IllegalArgumentException("User does not exists!");
        }
    }

    private boolean userExists(User user)
    {
        List<User> allRegisteredUsers = this.userRepository.getAll();
        return allRegisteredUsers.contains(user);
    }
}
